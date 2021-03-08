package com.wHealth.activities.clinicrequest

import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.activities.register.RegisterViewModel.Companion.DOCTOR
import com.wHealth.activities.register.RegisterViewModel.Companion.PATIENT
import com.wHealth.activities.ui.home.HomeFragment
import com.wHealth.di.activityScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_clinic_profile.*
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel


class ClinicProfileActivity : BaseActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    private val viewModel: ClinicRequestViewModel by activityScope.viewModel(this)
    var docId:Int=0
    var clinicId:Int?=0
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

        docId=user.id
        clinicId=clickedClinic?.id

         viewModel.cReqSuccessLiveData.observe(this, Observer { response->this
            if(response.status)
            {
                Toast.makeText(this,response.message, Toast.LENGTH_SHORT).show()
                //moveToMainActivity()
            }
            else
            {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
            //signUpProgressBar.hide()
        })

        if(user.type== PATIENT || user.type==CLINIC)
        {
            sendRequestToClinic.visibility = GONE

        }
        else if(user.type==CLINIC)
        {
            RegText.visibility = GONE
            clinicRegistrationNo.visibility = GONE
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
        viewModel.reqToJoinClinic(docId,clinicId)
      // val t =  Toast.makeText(this, "YOUR REQUEST HAS BEEN SENT TO CLINIC. WAIT UNTIL CLINIC APPROVES THE REQUEST!!", LENGTH_LONG)
        //t.show()
        val fragmentA = HomeFragment()

        supportFragmentManager.beginTransaction().replace(R.id.cf1,fragmentA).commit()
        sendRequestToClinic.visibility = GONE
        clinicEmail.visibility = GONE

        //val i = Intent(this@ClinicProfileActivity, HomeFragment::class.java )
        //startActivity(i)
    }



}