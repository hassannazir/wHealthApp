package com.wHealth.activities.clinicdoctors

import com.wHealth.model.AppUser

interface ClinicDoctorClickListener {
    fun onClinicdoctorClickListener(data: AppUser,clinic:Int)
    fun onratedoctorClickListener(data: AppUser,clinic:Int)
}