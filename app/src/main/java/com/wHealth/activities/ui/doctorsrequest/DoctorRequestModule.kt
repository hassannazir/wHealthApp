package com.wHealth.activities.ui.doctorsrequest

import androidx.annotation.RestrictTo
import com.wHealth.adapters.ClinicAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val doctorRequestModule = module {
    scope<DoctorRequestFragment> {
        viewModel {
            DoctorRequestViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
