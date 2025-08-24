package com.fay.ui.appointments

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.fay.data.appointments.AppointmentsRepository
import com.fay.data.appointments.model.Appointment
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.time.Clock

@OptIn(ExperimentalTime::class)
@Inject
@CircuitInject(AppScreen.Appointments::class, UiScope::class)
class AppointmentsPresenter(
    @Assisted private val navigator: Navigator,
    private val appointmentsRepository: AppointmentsRepository,
) : Presenter<AppointmentsPresenter.UiState> {
    enum class Tab { Upcoming, Past }

    data class DisplayAppointment(
        val id: String,
        val monthAbbrev: String,
        val day: String,
        val timeRange: String,
        val title: String,
        val timeZoneId: String,
    )

    data class UiState(
        val selectedTab: Tab,
        val upcoming: List<DisplayAppointment>,
        val past: List<DisplayAppointment>,
        val isLoading: Boolean = false,
        val error: String? = null,
        val eventSink: (Event) -> Unit = {},
    ) : CircuitUiState

    sealed class Event {
        data class TabSelected(val tab: Tab) : Event()
    }

    @Composable
    override fun present(): UiState {
        var selectedTab by remember { mutableStateOf(Tab.Upcoming) }
        var isLoading by remember { mutableStateOf(true) }
        var error by remember { mutableStateOf<String?>(null) }
        var upcoming by remember { mutableStateOf<List<DisplayAppointment>>(emptyList()) }
        var past by remember { mutableStateOf<List<DisplayAppointment>>(emptyList()) }

        LaunchedEffect(Unit) {
            isLoading = true
            error = null
            val result = appointmentsRepository.getListOfAppointments()
            result.fold(onSuccess = { list ->
                val nowUtc = Clock.System.now().toLocalDateTime(TimeZone.UTC)
                val (up, pastList) = list.appointments.partition {
                    val startInstant = Instant.parse(it.start)
                    val startUtc = startInstant.toLocalDateTime(TimeZone.UTC)
                    startUtc > nowUtc
                }
                upcoming = up.sortedBy { it.start }.map { mapToDisplay(it) }
                past = pastList.sortedByDescending { it.start }.map { mapToDisplay(it) }
                isLoading = false
            }, onFailure = { e ->
                error = e.message ?: "Unknown error"
                isLoading = false
            })
        }

        return UiState(
            selectedTab = selectedTab,
            upcoming = upcoming,
            past = past,
            isLoading = isLoading,
            error = error,
        ) { event ->
            when (event) {
                is Event.TabSelected -> selectedTab = event.tab
            }
        }
    }

    fun LocalDateTime.formatTime(): String {
        val hour12 = ((hour % 12).let { if (it == 0) 12 else it })
        val amPm = if (hour < 12) "AM" else "PM"
        return "$hour12:${minute.toString().padStart(2, '0')} $amPm"
    }

    fun mapToDisplay(appointment: Appointment): DisplayAppointment {
        val tz = TimeZone.currentSystemDefault()

        val startUtc = Instant.parse(appointment.start)
        val endUtc = Instant.parse(appointment.end)
        val localStart = startUtc.toLocalDateTime(tz)
        val localEnd = endUtc.toLocalDateTime(tz)
        return DisplayAppointment(
            id = appointment.appointmentId,
            monthAbbrev = localStart.date.month.name.take(3),
            day = localStart.date.day.toString(),
            timeRange = "${localStart.formatTime()} - ${localEnd.formatTime()}",
            title = appointment.appointmentType.ifBlank { "Follow up" },
            timeZoneId = tz.id,
        )
    }
}