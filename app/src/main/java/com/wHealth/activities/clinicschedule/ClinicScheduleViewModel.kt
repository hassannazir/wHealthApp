package com.wHealth.activities.clinicschedule
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.ClinicReqResponse
import com.wHealth.network.response.LoginResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClinicScheduleViewModel(private val apiInterface: ApiInterface,private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var clinicScheduleSuccessLiveData: MutableLiveData<ClinicReqResponse> = MutableLiveData()

    fun scheduleClinic(clinicId: Int,startDate:String,endDate:String,startTime:String,endTime:String,day:String,recurring:Boolean) {
        var docId= sharedPreference.getCurrentUser().id
        launch {
            val response = apiInterface.clinicScheduleApi(docId,clinicId,startTime,endTime,startDate,endDate,recurring,day)
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
