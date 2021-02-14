package com.wHealth.network.response

import com.google.gson.annotations.SerializedName
import com.wHealth.model.AppUser

data class LoginResponse(
    val status: Boolean,
    val result: AppUser,
    val token: String?,
    val message: String
)