package com.fay.data.appointments.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AppointmentsList(
    val appointments: List<Appointment>,
)

@Serializable
data class Appointment(
    @SerialName("appointment_id") val appointmentId: String,
    @SerialName("patient_id") val patientId: String,
    @SerialName("provider_id") val providerId: String,
    @SerialName("status") val status: String,
    @SerialName("appointment_type") val appointmentType: String,
    @SerialName("start") val start: String,
    @SerialName("end") val end: String,
    @SerialName("duration_in_minutes") val duration: Int,
    @SerialName("recurrence_type") val recurrenceType: String,
)