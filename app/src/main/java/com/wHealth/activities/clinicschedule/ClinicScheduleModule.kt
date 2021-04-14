package com.wHealth.activities.clinicschedule

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ClinicScheduleModule = module {
    scope<ClinicScheduleActivity> {
        viewModel {
            ClinicScheduleViewModel(apiInterface = get())
        }
    }
}
