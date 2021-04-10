package com.wHealth.activities.ui.doctor

import androidx.annotation.RestrictTo
import com.wHealth.adapters.ClinicAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val doctorModule = module {
    scope<DoctorFragment> {
        viewModel {
            DoctorViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
