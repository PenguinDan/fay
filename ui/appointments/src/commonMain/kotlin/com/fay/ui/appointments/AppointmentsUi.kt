package com.fay.ui.appointments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fay.core.base.inject.UiScope
import com.fay.core.ui.navigation.AppScreen
import com.fay.core.ui.theme.spacing
import com.slack.circuit.codegen.annotations.CircuitInject

@Composable
@CircuitInject(AppScreen.Appointments::class, UiScope::class)
fun AppointmentsUi(
    state: AppointmentsPresenter.UiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = MaterialTheme.spacing.large)
    ) {
        Spacer(Modifier.height(MaterialTheme.spacing.large))
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Appointments",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
            )
        }

        Spacer(Modifier.height(MaterialTheme.spacing.large))

        val tabs = remember { listOf(AppointmentsPresenter.Tab.Upcoming, AppointmentsPresenter.Tab.Past) }
        TabRow(
            selectedTabIndex = tabs.indexOf(state.selectedTab),
            divider = { HorizontalDivider() },
        ) {
            tabs.forEach { tab ->
                Tab(
                    selected = tab == state.selectedTab,
                    onClick = { state.eventSink(AppointmentsPresenter.Event.TabSelected(tab)) },
                    text = {
                        Text(
                            text = when (tab) {
                                AppointmentsPresenter.Tab.Upcoming -> "Upcoming"
                                AppointmentsPresenter.Tab.Past -> "Past"
                            }
                        )
                    },
                )
            }
        }

        Spacer(Modifier.height(MaterialTheme.spacing.medium))

        val appointments = when (state.selectedTab) {
            AppointmentsPresenter.Tab.Upcoming -> state.upcoming
            AppointmentsPresenter.Tab.Past -> state.past
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        ) {
            itemsIndexed(appointments, key = { _, a -> a.id }) { index, appt ->
                AppointmentCard(
                    appointment = appt,
                    showJoin = state.selectedTab == AppointmentsPresenter.Tab.Upcoming && index == 0,
                )
            }
        }
    }
}

@Composable
private fun AppointmentCard(
    appointment: AppointmentsPresenter.DisplayAppointment,
    showJoin: Boolean,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = if (showJoin) 3.dp else 1.dp),
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Date pill
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = appointment.monthAbbrev,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = appointment.day,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                }

                Column(modifier = Modifier.padding(start = MaterialTheme.spacing.medium)) {
                    Text(
                        text = "${appointment.timeRange} (${appointment.timeZoneId})",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = appointment.title,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            if (showJoin) {
                Spacer(Modifier.height(MaterialTheme.spacing.medium))
                OutlinedButton(
                    onClick = { /* no-op */ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Text("Join appointment")
                }
            }
        }
    }
}