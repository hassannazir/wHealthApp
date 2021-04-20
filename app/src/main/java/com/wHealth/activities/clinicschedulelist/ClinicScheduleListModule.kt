package com.wHealth.activities.ui.clinicschedulelist

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ClinicScheduleListModule = module {
    scope<ClinicScheduleListActivity> {
        viewModel {
            ClinicScheduleListViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
