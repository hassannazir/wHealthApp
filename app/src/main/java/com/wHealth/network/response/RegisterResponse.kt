package com.wHealth.network.response

import com.google.gson.annotations.SerializedName
data class RegisterResponse(
    val status: Boolean,
    val result: String
)