package com.wHealth.network.response

import java.io.Serializable

data class FeedbackListResponse(
    val status: Boolean,
    val result: List<feedback>,
    val token: String?,
    val message: String
)
data class feedback(
    val patientName: String? = "",
    val clinicName: String? ="",
    val rating: Float = 0F,
    val review: String? = ""
) : Serializable {}