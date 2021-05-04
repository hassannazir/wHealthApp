package com.wHealth.activities.ui.allClinics

import com.wHealth.model.AppUser

interface ClinicClickListener {
    fun onClinicClickListener(data: AppUser)
    fun onAvailableDoctorClickListener(data: AppUser)
}