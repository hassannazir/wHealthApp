package com.wHealth.activities.ui.ratedoctors

import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.ClinicReqResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RateDoctorsViewModel (private val apiInterface: ApiInterface, private val sharedPreference: WHealthSharedPreference) : ViewModel(),
    CoroutineScope
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
        }

    var getInactiveDocSuccessLiveData: MutableLiveData<ClinicReqResponse> = MutableLiveData()

    fun submitFeedback(docId:Int, clinicId:Int, rating: Float, review:String) {
        var patientId=sharedPreference.getCurrentUser().id
        launch {
            val response = apiInterface.submitFeedback(patientId,docId,clinicId,rating,review)
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