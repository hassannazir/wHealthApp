package com.wHealth

import android.app.Application
import com.wHealth.activities.bookappointment.BookAppointmentModule
import com.wHealth.activities.bookappointment.BookingTimeModule
import com.wHealth.activities.clinicdoctors.ClinicDoctorListActivity
import com.wHealth.activities.clinicdoctors.ClinicDoctorListModule
import com.wHealth.activities.clinicrequest.clinicRequestModule
import com.wHealth.activities.clinicschedule.ClinicScheduleModule
import com.wHealth.activities.doctorprofile.doctorProfileModule
import com.wHealth.activities.forgotpassword.forgotPasswordModule
import com.wHealth.activities.register.registerModule
import com.wHealth.activities.login.loginModule
import com.wHealth.activities.ui.allClinics.AllClinicsModule
import com.wHealth.activities.ui.clinics.clinicsModule
import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListModule
import com.wHealth.activities.ui.doctor.doctorModule
import com.wHealth.activities.ui.doctorsrequest.doctorRequestModule
import com.wHealth.activities.updateprofile.updateProfileModule
import com.wHealth.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WHealthApplication : Application() {

    private val globalModules =
        listOf(
            appModule,
            registerModule,
            loginModule,
                forgotPasswordModule,
            updateProfileModule,
            clinicsModule,
                clinicRequestModule,
                doctorModule,
                doctorRequestModule,
                doctorProfileModule,
                AllClinicsModule,
                ClinicScheduleModule,
                ClinicScheduleListModule,
            ClinicDoctorListModule,
            BookAppointmentModule,
            BookingTimeModule

        )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WHealthApplication)
            modules(globalModules)
        }
    }
}
