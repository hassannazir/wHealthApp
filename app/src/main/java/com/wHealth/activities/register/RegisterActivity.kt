package com.wHealth.activities.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.login.LoginActivity
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.activities.register.RegisterViewModel.Companion.DOCTOR
import com.wHealth.activities.register.RegisterViewModel.Companion.PATIENT
import com.wHealth.di.activityScope
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.scope.viewModel

class RegisterActivity : BaseActivity() {
    private val viewModel: RegisterViewModel by activityScope.viewModel(this)
    private var type: String = "Doctor"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        signinText.setOnClickListener {
            finish()
        }

        signUp.setOnClickListener {
            validateAndSignUp()
        }

        registrationNo.visibility=View.GONE
        viewModel.registerSuccessLiveData.observe(this, Observer {response->this
            if(response.status)
            {
                Toast.makeText(this, response.result, Toast.LENGTH_SHORT).show()
                moveToLoginActivity()
            }
            else
            {
                Toast.makeText(this, response.result, Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_patient ->
                    if (checked) {
                        type = PATIENT
                        qualification.visibility = View.GONE
                        experience.visibility = View.GONE
                        licenseNo.visibility=View.GONE
                        registrationNo.visibility=View.GONE
                    }
                R.id.radio_doctor ->
                    if (checked) {
                        type = DOCTOR
                        qualification.visibility = View.VISIBLE
                        experience.visibility = View.VISIBLE
                        licenseNo.visibility=View.VISIBLE
                        registrationNo.visibility=View.GONE
                    }
                R.id.radio_clinic ->
                    if (checked) {
                        type = CLINIC
                        qualification.visibility = View.GONE
                        experience.visibility = View.GONE
                        licenseNo.visibility=View.GONE
                        registrationNo.visibility=View.VISIBLE
                    }
            }
        }
    }


    private fun moveToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }


    private fun validateAndSignUp() {

        val name: String = fullName.text.toString().trim()
        val emailAddress: String = email.text.toString().trim()
        val uName: String = userName.text.toString().trim()
        val pass: String = password.text.toString().trim()
        val phoneNumber: String = phoneNo.text.toString().trim()
        val qual: String = qualification.text.toString().trim()
        val exp: String = experience.text.toString().trim()
        val addr: String = address.text.toString().trim()
        val licenseNum: String = licenseNo.text.toString().trim()
        val regNo:String=registrationNo.text.toString().trim();


        if (regNo.isEmpty()) {
            registrationNo.error = "Registration Number Required"
            registrationNo.requestFocus()
            return
        }
        if (name.isEmpty()) {
            fullName.error = "Name Required"
            fullName.requestFocus()
            return
        }
        if (emailAddress.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            email.error = "Valid Email Required"
            email.requestFocus()
            return
        }
        //Password Authentication
        if (pass.isEmpty()) {
            password.error = "Password Required."
            password.requestFocus()
            return
        }
        if (phoneNumber.isEmpty()) {
            phoneNo.error = "Phone Number Required"
            phoneNo.requestFocus()
            return
        }

        if (uName.isEmpty()) {
            userName.error = "User Name Required"
            userName.requestFocus()
            return
        }

        if (type == "Doctor") {
            if (licenseNum.isEmpty()) {
                licenseNo.error = "License Number Required"
                licenseNo.requestFocus()
                return
            }
            if (qual.isEmpty()) {
                qualification.error = "Qualification required"
                qualification.requestFocus()
                return
            }
            if (exp.isEmpty()) {
                experience.error = "Experience required"
                experience.requestFocus()
                return
            }
        }

        if (addr.isEmpty()) {
            address.error = "Address required"
            address.requestFocus()
            return
        }

        //signUpProgressBar.show()

        viewModel.registerUser(regNo,name, emailAddress, phoneNumber, addr, uName, pass, type, licenseNum,
            qual, exp)
    }

}