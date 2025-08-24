package com.fay.data.appointments

import com.fay.core.base.inject.ApplicationScope
import com.fay.core.base.inject.UiScope
import com.fay.core.network.ktor.safeApiCall
import com.fay.data.appointments.model.AppointmentsList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

interface AppointmentsRepository {
    suspend fun getListOfAppointments(): Result<AppointmentsList>
}

@Inject
@ContributesBinding(UiScope::class)
class AppointmentsRepositoryImpl(
    private val httpClient: HttpClient,
) : AppointmentsRepository {
    override suspend fun getListOfAppointments(): Result<AppointmentsList> {
        return safeApiCall {
            httpClient.get("/appointments").body<AppointmentsList>()
        }
    }
}