package com.wHealth.activities.ui.allClinics

import com.wHealth.activities.ui.clinics.clinicsModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val AllClinicsModule = module {
    scope<AllClinicFragment> {
        viewModel {
            AllClinicViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
