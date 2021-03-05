package com.wHealth.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.wHealth.R
import com.wHealth.activities.register.RegisterActivity
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.activities.register.RegisterViewModel.Companion.PATIENT
import com.wHealth.activities.ui.home.HomeFragment
import com.wHealth.activities.updateprofile.UpdateProfileActivity
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_clinic_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import org.koin.android.ext.android.inject

class ClinicProfileActivity : BaseActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic_profile)


        val clickedClinic = intent.getSerializableExtra("clickedClinic") as? AppUser
        var user=sharedPreference.getCurrentUser()

        clinicName.text=clickedClinic?.name
        clinicEmail.text=clickedClinic?.email
        clinicAddress.text=clickedClinic?.address
        clinicPhone.text=clickedClinic?.phoneNo
        clinicRegistrationNo.text=clickedClinic?.registrationNo


        if(user.type== PATIENT)
        {
            sendRequestToClinic.visibility = GONE

        }
        sendRequestToClinic.setOnClickListener({
            moveToHome()
        })
//        if(user.type==PATIENT || user.type==CLINIC)
//        {
//            profileName.text=user.name
//            profileEmail.text=user.email
//            profilePhone.text=user.phoneNo
//            profileAddress.text=user.address
//            licenseText.visibility=GONE
//            profileLicense.visibility=GONE
//            lineFour.visibility= GONE
//            lineFive.visibility= GONE
//            lineSix.visibility= GONE
//            qualificationText.visibility=GONE
//            profileQualification.visibility= GONE
//            experienceText.visibility= GONE
//            profileExperience.visibility=GONE
//        }
//        else
//        {
//            profileName.text=user.name
//            profileEmail.text=user.email
//            profilePhone.text=user.phoneNo
//            profileAddress.text=user.address
//            profileLicense.text=user.licenseNo
//            profileQualification.text=user.qualification
//            profileExperience.text=user.experience
//        }
    }

    private fun moveToHome()
    {
       val t =  Toast.makeText(this, "YOUR REQUEST HAS BEEN SENT TO CLINIC. WAIT UNTIL CLINIC APPROVES THE REQUEST!!", LENGTH_LONG)
        t.show()
        val i = Intent(this@ClinicProfileActivity, HomeFragment::class.java)
        startActivity(i)
    }

}