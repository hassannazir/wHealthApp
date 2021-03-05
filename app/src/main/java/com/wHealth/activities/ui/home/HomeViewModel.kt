package com.wHealth.activities.ui.home
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.GetAllClinicsResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val apiInterface: ApiInterface,private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var getClinicsSuccessLiveData: MutableLiveData<GetAllClinicsResponse> = MutableLiveData()

    fun getActiveClinics() {

        launch {
            val response = apiInterface.getActiveClinicsApi()
            if (response.isSuccessful) {

                response.body()?.let { response ->
                    getClinicsSuccessLiveData.postValue(response)
                }
            }
            else
            {
                getClinicsSuccessLiveData.postValue(null)
            }
        }
    }

}
