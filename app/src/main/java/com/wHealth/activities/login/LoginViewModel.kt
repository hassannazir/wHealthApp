package com.wHealth.activities.login
import ApiInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.network.response.LoginResponse
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel(private val apiInterface: ApiInterface,private val sharedPreference: WHealthSharedPreference) : ViewModel(), CoroutineScope
{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var loginSuccessLiveData: MutableLiveData<LoginResponse> = MutableLiveData()

    fun loginUser(username: String, pass: String) {

        launch {
            val response = apiInterface.loginUserApi(username,pass)
            if (response.isSuccessful) {

                response.body()?.let { response ->
                    loginSuccessLiveData.postValue(response)

                    sharedPreference.saveUser(response.result.copy(username = username))
                    sharedPreference.saveToken(response.token)
                }

            }
            else
            {
                    loginSuccessLiveData.postValue(null)
            }
        }
    }

}
