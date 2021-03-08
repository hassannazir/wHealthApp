package com.wHealth.activities.clinicrequest
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.ClinicReqResponse
import com.wHealth.network.response.LoginResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClinicRequestViewModel(private val apiInterface: ApiInterface) : ViewModel(), CoroutineScope
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var cReqSuccessLiveData: MutableLiveData<ClinicReqResponse> = MutableLiveData()

    fun reqToJoinClinic(docId: Int, clinicId: Int?) {

        launch {
            val response = apiInterface.reqToJoinClinicApi(docId,clinicId)
            if (response.isSuccessful) {

                response.body()?.let { response ->
                    cReqSuccessLiveData.postValue(response)

                }

            }
            else
            {
                cReqSuccessLiveData.postValue(null)
            }
        }
    }

}
