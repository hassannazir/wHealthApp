package com.wHealth.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppUser(
    val id:Int,
    val name: String,
    val email: String,
    val phoneNo: String,
    val address: String,
    val username: String,
    val password: String,
    val type: String,
    val licenseNo: String,
    val qualification: String,
    val experience: String
) : Serializable