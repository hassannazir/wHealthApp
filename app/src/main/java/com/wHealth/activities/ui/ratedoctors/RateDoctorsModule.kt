package com.wHealth.activities.ui.ratedoctors

import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListActivity
import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val RateDoctorsModule = module {
    scope<RateDoctorsActivity> {
        viewModel {
            RateDoctorsViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}