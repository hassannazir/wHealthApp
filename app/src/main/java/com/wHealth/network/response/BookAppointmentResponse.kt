package com.wHealth.network.response

import java.io.Serializable

class BookAppointmentResponse (
    val status: Boolean,
    val result: List<Booking>,
    val token: String?,
    val message: String
    )
data class Booking(
    val appointmentId: Int = 0,
    val patientName: String? = "",
    val clinicName: String? = "",
    val startTime: String? = "",
    val endTime: String? ="",
    val date: String? = "",
    val status:Int=0
) : Serializable {}