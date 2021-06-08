package com.wHealth.activities.ui.clinicschedulelist
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.network.response.ClinicReqResponse
import com.wHealth.network.response.ClinicScheduleListResponse
import com.wHealth.network.response.GetAllClinicsResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClinicScheduleListViewModel(private val apiInterface: ApiInterface, private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{
    var cId:Int=0

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var getInactiveDocSuccessLiveData: MutableLiveData<ClinicScheduleListResponse> = MutableLiveData()
    var getAppointmentSuccessLiveData: MutableLiveData<ClinicReqResponse> = MutableLiveData()


    fun getClinicSchedule(docId:Int,clinicId:Int?) {
        launch {
                val response = apiInterface.getClinicScheduleListApi(docId,clinicId)
                if (response.isSuccessful) {
                    response.body()?.let { response ->
                        getInactiveDocSuccessLiveData.postValue(response)
                    }
                } else {
                    getInactiveDocSuccessLiveData.postValue(null)
                }
        }
    }


}
