package com.wHealth.activities.clinicrequest

import androidx.annotation.RestrictTo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val clinicRequestModule = module {
    scope<ClinicProfileActivity> {
        viewModel {
            ClinicRequestViewModel(apiInterface = get())
        }
    }
}
