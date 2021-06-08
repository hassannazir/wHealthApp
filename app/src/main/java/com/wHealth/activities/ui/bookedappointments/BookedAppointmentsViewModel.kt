package com.wHealth.activities.ui.bookedappointments

import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.BookAppointmentResponse
import com.wHealth.network.response.Booking
import com.wHealth.network.response.ClinicReqResponse
import com.wHealth.network.response.GetAllClinicsResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class BookedAppointmentsViewModel(private val apiInterface: ApiInterface, private val sharedPreference: WHealthSharedPreference) : ViewModel(),
    CoroutineScope
{
    var cId:Int=0

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var getInactiveDocSuccessLiveData: MutableLiveData<BookAppointmentResponse> = MutableLiveData()
    var getAppointmentSuccessLiveData: MutableLiveData<ClinicReqResponse> = MutableLiveData()


    fun getAppointments() {
        launch {
            var docId= sharedPreference.getCurrentUser().id
                val  response = apiInterface.PendingAppointments(docId)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        getInactiveDocSuccessLiveData.postValue(response)
                    }
                } else {
                    getInactiveDocSuccessLiveData.postValue(null)
                }
        }
    }
    fun getBookedAppointments() {
        launch {
            var docId= sharedPreference.getCurrentUser().id

            val response=apiInterface.BookingAppointments(docId)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    getInactiveDocSuccessLiveData.postValue(response)
                }
            } else {
                getInactiveDocSuccessLiveData.postValue(null)
            }
        }
    }
    fun getPatientAppointments() {
        launch {
            var patientId= sharedPreference.getCurrentUser().id
            val  response = apiInterface.PatientBookedAppointments(patientId)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    getInactiveDocSuccessLiveData.postValue(response)
                }
            } else {
                getInactiveDocSuccessLiveData.postValue(null)
            }
        }
    }
    fun approveRequest(appointmentId:Int,status:Boolean) {
        launch {
            val response = apiInterface.ApproveCancelAppointment(appointmentId,status)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    getAppointmentSuccessLiveData.postValue(response)
                }
            } else {
                getAppointmentSuccessLiveData.postValue(null)
            }
        }
    }


}
