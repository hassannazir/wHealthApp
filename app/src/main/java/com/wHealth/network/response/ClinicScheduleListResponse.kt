package com.wHealth.network.response

import com.google.gson.annotations.SerializedName
import com.wHealth.model.AppUser
import java.io.Serializable

data class ClinicScheduleListResponse(
    val status: Boolean,
    val result: List<clinicSchedule>,
    val token: String?,
    val message: String
)
data class clinicSchedule(
        val id: Int = 0,
        val doctorId: Int = 0,
        val clinicId: Int = 0,
        val day: String? = "",
        val recurring: Boolean? =false,
        val startDate: String? = "",
        val endDate: String? = "",
        val startTime: String? = "",
        val endTime: String? = "",
        val slotLength: Int? = 0
) : Serializable {}