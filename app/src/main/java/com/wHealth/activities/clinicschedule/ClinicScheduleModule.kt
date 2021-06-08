package com.wHealth.activities.clinicschedule

import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ClinicScheduleModule = module {
    scope<ClinicScheduleActivity> {
        viewModel {
            ClinicScheduleViewModel(apiInterface = get(),sharedPreference = get())
        }
    }
}
