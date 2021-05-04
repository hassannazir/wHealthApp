package com.wHealth.activities.clinicdoctors

import androidx.annotation.RestrictTo
import com.wHealth.activities.ui.clinics.ClinicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ClinicDoctorListModule = module {
    scope<ClinicDoctorListActivity> {
        viewModel {
            ClinicViewModel(apiInterface = get(),sharedPreference = get())
        }
    }
}
