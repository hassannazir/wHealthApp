package com.wHealth.network.response

import java.io.Serializable

data class BookingSlotsResponse(
    val status: Boolean,
    val result: List<BookingSlot>,
    val token: String?,
    val message: String
)
data class BookingSlot(
    val doctorId: Int = 0,
    val clinicId: Int = 0,
    val date: String? = "",
    val startTime: String? = "",
    val endTime: String? = "",
    val status:Int=0
) : Serializable {}