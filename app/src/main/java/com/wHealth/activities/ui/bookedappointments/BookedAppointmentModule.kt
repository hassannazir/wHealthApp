package com.wHealth.activities.ui.bookedappointments

import com.wHealth.activities.ui.allClinics.AllClinicFragment
import com.wHealth.activities.ui.allClinics.AllClinicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val BookedAppointmentModule = module {
    scope<BookedAppointmentsFragment> {
        viewModel {
            BookedAppointmentsViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
