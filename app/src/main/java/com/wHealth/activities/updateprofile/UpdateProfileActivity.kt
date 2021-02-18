package com.wHealth.activities.updateprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.MainActivity
import com.wHealth.activities.register.RegisterViewModel
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.activities.register.RegisterViewModel.Companion.DOCTOR
import com.wHealth.activities.register.RegisterViewModel.Companion.PATIENT
import com.wHealth.di.activityScope
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_update_profile.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel

class UpdateProfileActivity : BaseActivity() {
    private val viewModel: UpdateProfileViewModel by activityScope.viewModel(this)
    private  val sharedPreference: WHealthSharedPreference by inject()
    private var user=sharedPreference.getCurrentUser()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        updateProfile.setOnClickListener {
            validateAndUpdate()
        }

        viewModel.updateSuccessLiveData.observe(this, Observer {response->this
            if(response.status)
            {
                Toast.makeText(this, response.result, Toast.LENGTH_SHORT).show()
                moveToMainActivity()
            }
            else
            {
                Toast.makeText(this, response.result, Toast.LENGTH_SHORT).show()
            }

        })

        fullNameEdit.setText(user.name)
        emailEdit.setText(user.email)
        emailEdit.setEnabled(false);
        phoneNoEdit.setText(user.phoneNo)
        addressEdit.setText(user.address)


        if(user.type==PATIENT || user.type==CLINIC) {
            qualificationEdit.visibility= View.GONE
            experienceEdit.visibility= View.GONE
            licenseNoEdit.visibility= View.GONE
        }
        else if(user.type==DOCTOR) {
            qualificationEdit.setText(user.qualification)
            licenseNoEdit.setText(user.licenseNo)
            licenseNoEdit.setEnabled(false);
            experienceEdit.setText(user.experience)
        }

    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    private fun validateAndUpdate() {

        val name: String = fullNameEdit.text.toString().trim()
        val phoneNumber: String = phoneNoEdit.text.toString().trim()
        val addr: String = addressEdit.text.toString().trim()
        val qual: String = qualificationEdit.text.toString().trim()
        val exp: String = experienceEdit.text.toString().trim()
        val licenseNum: String = licenseNoEdit.text.toString().trim()


        if (name.isEmpty()) {
            fullNameEdit.error = "Name Required"
            fullNameEdit.requestFocus()
            return
        }

        if (phoneNumber.isEmpty()) {
            phoneNoEdit.error = "Phone Number Required"
            phoneNoEdit.requestFocus()
            return
        }


        if (user.type == "Doctor") {
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
            addressEdit.error = "Address required"
            addressEdit.requestFocus()
            return
        }

        //signUpProgressBar.show()

        viewModel.updateUser(user.id,name, user.email, phoneNumber, addr, user.username, user.password, user.type, licenseNum,
            qual, exp)
    }
}