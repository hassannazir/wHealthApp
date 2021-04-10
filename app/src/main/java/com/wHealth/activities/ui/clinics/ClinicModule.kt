package com.wHealth.activities.ui.clinics

import com.wHealth.activities.ui.clinics.clinicsModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val clinicsModule = module {
    scope<ClinicFragment> {
        viewModel {
            ClinicViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
