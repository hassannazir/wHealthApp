package com.wHealth.activities.updateprofile
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.model.AppUser
import com.wHealth.network.response.RegisterResponse
import com.wHealth.network.response.UpdatePasswordResponse
import com.wHealth.network.response.UpdateProfileResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UpdateProfileViewModel(
    private val apiInterface: ApiInterface
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var updateSuccessLiveData: MutableLiveData<UpdateProfileResponse> = MutableLiveData()

    fun updateUser(id:Int ,name: String, email: String, phoneNo: String, address: String, userName: String, password: String, type: String, licenseNo: String,
        qualification: String, experience: String) {

        launch {
            val appUser = AppUser(id,name, email, phoneNo, address, userName, password, type, licenseNo,qualification,experience)
            val response = apiInterface.updateUserApi(id,appUser)
            if (response.isSuccessful) {
                updateSuccessLiveData.postValue(response.body())
            }
            else
            {
                updateSuccessLiveData.postValue(null)
            }
        }
    }

}
