package com.wHealth.activities.doctorprofile

import androidx.annotation.RestrictTo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val doctorProfileModule = module {
    scope<DoctorProfileActivity> {
        viewModel {
            DoctorProfileViewModel(apiInterface = get())
        }
    }
}
