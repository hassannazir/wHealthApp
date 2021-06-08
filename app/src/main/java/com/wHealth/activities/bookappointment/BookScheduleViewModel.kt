package com.wHealth.activities.bookappointment
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.ClinicReqResponse
import com.wHealth.network.response.LoginResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class BookScheduleViewModel(private val apiInterface: ApiInterface, private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var clinicScheduleSuccessLiveData: MutableLiveData<ClinicReqResponse> = MutableLiveData()

    fun booktiming(doctorId:Int,clinicId:Int,status:Int,startTime:String,endTime:String,date:String,scheduleId:Int) {
        var patientId= sharedPreference.getCurrentUser().id
        launch {
            val response = apiInterface.bookSchedule(patientId,doctorId,clinicId,status,startTime,date,endTime)
            if (response.isSuccessful) {
                response.body()?.let { response ->
                    clinicScheduleSuccessLiveData.postValue(response)
                }
            }
            else
            {
                clinicScheduleSuccessLiveData.postValue(null)
            }
        }
    }

}
