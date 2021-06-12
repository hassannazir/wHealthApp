package com.wHealth.activities.ui.removeclinic

import com.wHealth.activities.ui.ratedoctors.RateDoctorsActivity
import com.wHealth.activities.ui.ratedoctors.RateDoctorsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val RemoveClinicModule = module {
    scope<RemoveClinicFragment> {
//        viewModel {
//            RateDoctorsViewModel(apiInterface = get(), sharedPreference=get())
//        }
    }
}