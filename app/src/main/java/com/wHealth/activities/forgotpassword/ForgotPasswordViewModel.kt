package com.wHealth.activities.forgotpassword
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.ForgotPasswordResponse
import com.wHealth.network.response.LoginResponse
import com.wHealth.network.response.UpdatePasswordResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ForgotPasswordViewModel(private val apiInterface: ApiInterface, private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var verifySuccessLiveData: MutableLiveData<ForgotPasswordResponse> = MutableLiveData()
    var newPassSuccessLiveData: MutableLiveData<UpdatePasswordResponse> = MutableLiveData()

    fun verifyUser(usrname: String) {

        launch {
            val response = apiInterface.verifyUserApi(usrname)
            if (response.isSuccessful) {
                    verifySuccessLiveData.postValue(response.body())

            }
            else
            {
                   verifySuccessLiveData.postValue(null)
            }
        }
    }

     fun UpdatePass(usrname:String,newpass:String)
     {
         launch {
             val response = apiInterface.UpdatePassApi(usrname,newpass)
             if (response.isSuccessful) {
                 newPassSuccessLiveData.postValue(response.body())

             }
             else
             {
                 newPassSuccessLiveData.postValue(null)
             }
         }

     }

}
