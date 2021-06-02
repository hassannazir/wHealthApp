package com.wHealth.activities.bookappointment


import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val BookAppointmentModule = module {
    scope<BookAppointmentActivity> {
        viewModel {
            ClinicScheduleListViewModel(apiInterface = get(),sharedPreference = get())
        }
    }
}
val BookingTimeModule = module {
    scope<BookingTimeActivity> {
        viewModel {
            BookScheduleViewModel(apiInterface = get(),sharedPreference = get())
        }
    }
}