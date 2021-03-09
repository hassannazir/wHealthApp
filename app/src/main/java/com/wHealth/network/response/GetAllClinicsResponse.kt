package com.wHealth.network.response

import com.google.gson.annotations.SerializedName
import com.wHealth.model.AppUser

data class  GetAllClinicsResponse(
    val status: Boolean,
    val result: List<AppUser>,
    val message: String?
)