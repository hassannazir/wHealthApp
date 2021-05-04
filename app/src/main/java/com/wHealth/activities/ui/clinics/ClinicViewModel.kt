package com.wHealth.activities.ui.clinics
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.network.response.GetAllClinicsResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ClinicViewModel(private val apiInterface: ApiInterface, private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var getInactiveDocSuccessLiveData: MutableLiveData<GetAllClinicsResponse> = MutableLiveData()


    fun getUsers(cId:Int) {
        launch {

                val response = apiInterface.getActiveDoctorsApi(cId)
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
