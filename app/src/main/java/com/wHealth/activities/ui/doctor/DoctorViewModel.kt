package com.wHealth.activities.ui.doctor
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.network.response.GetAllClinicsResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DoctorViewModel(private val apiInterface: ApiInterface, private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{
    var cId:Int=0
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var getClinicsSuccessLiveData: MutableLiveData<GetAllClinicsResponse> = MutableLiveData()


    fun getUsers() {
        cId= sharedPreference.getCurrentUser().id
        launch {

            if(sharedPreference.getCurrentUser().type!=CLINIC) {
                val response = apiInterface.getWorkingClinicsApi(cId)
                if (response.isSuccessful) {

                    response.body()?.let { response ->
                        getClinicsSuccessLiveData.postValue(response)
                    }
                } else {
                    getClinicsSuccessLiveData.postValue(null)
                }
            }
            else
            {
                val response = apiInterface.getActiveDoctorsApi(cId)
                if (response.isSuccessful) {

                    response.body()?.let { response ->
                        getClinicsSuccessLiveData.postValue(response)
                    }
                } else {
                    getClinicsSuccessLiveData.postValue(null)
                }
            }
        }
    }

}
