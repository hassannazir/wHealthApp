package com.wHealth.network.response

import com.google.gson.annotations.SerializedName
import com.wHealth.model.AppUser

data class UpdatePasswordResponse(
    val status: Boolean,
    val result: String?,
    val token: String?,
    val message: String?
)