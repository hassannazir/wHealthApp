package com.wHealth.activities.register

import ApiInterface
import android.app.DownloadManager
import android.app.VoiceInteractor
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wHealth.R
import com.wHealth.model.AppUser
import com.wHealth.network.response.RegisterResponse
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.*
import java.lang.ref.ReferenceQueue
import org.json.JSONObject;
import java.util.logging.Logger
import kotlin.coroutines.CoroutineContext

class RegisterViewModel(
    private val apiInterface: ApiInterface
) : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { _, throwable ->
            //logger.e("TasksViewModel", "Error on coroutine", throwable)
        }

    var registerSuccessLiveData: MutableLiveData<RegisterResponse> = MutableLiveData()

    fun registerUser(name: String, email: String, phoneNo: String, address: String, userName: String, password: String, type: String, licenseNo: String,
        qualification: String, experience: String) {

        launch {
            val appUser = AppUser(0,name, email, phoneNo, address, userName, password, type, licenseNo,qualification,experience)
            val response = apiInterface.registerUserApi(appUser)
            if (response.isSuccessful) {
                    registerSuccessLiveData.postValue(response.body())
            }
            else
            {
                registerSuccessLiveData.postValue(null)
            }
        }
    }

    companion object {
        val PATIENT="Patient"
        val DOCTOR="Doctor"
        val CLINIC="Clinic"
    }
}
