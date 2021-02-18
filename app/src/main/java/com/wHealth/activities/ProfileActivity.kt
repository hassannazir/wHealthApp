package com.wHealth.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import com.wHealth.R
import com.wHealth.activities.register.RegisterActivity
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.activities.register.RegisterViewModel.Companion.PATIENT
import com.wHealth.activities.updateprofile.UpdateProfileActivity
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_profile.*
import org.koin.android.ext.android.inject

class ProfileActivity : BaseActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileEdit.setOnClickListener {
            moveToUpdateProfileActivity()
        }

        var user=sharedPreference.getCurrentUser()
        if(user.type==PATIENT || user.type==CLINIC)
        {
            profileName.text=user.name
            profileEmail.text=user.email
            profilePhone.text=user.phoneNo
            profileAddress.text=user.address
            licenseText.visibility=GONE
            profileLicense.visibility=GONE
            lineFour.visibility= GONE
            lineFive.visibility= GONE
            lineSix.visibility= GONE
            qualificationText.visibility=GONE
            profileQualification.visibility= GONE
            experienceText.visibility= GONE
            profileExperience.visibility=GONE
        }
        else
        {
            profileName.text=user.name
            profileEmail.text=user.email
            profilePhone.text=user.phoneNo
            profileAddress.text=user.address
            profileLicense.text=user.licenseNo
            profileQualification.text=user.qualification
            profileExperience.text=user.experience
        }
    }

    private fun moveToUpdateProfileActivity()
    {
        val i = Intent(this@ProfileActivity, UpdateProfileActivity::class.java)
        startActivity(i)
    }
}