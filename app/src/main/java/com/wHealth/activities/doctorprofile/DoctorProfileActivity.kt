package com.wHealth.activities.doctorprofile

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.MainActivity
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.activities.register.RegisterViewModel.Companion.PATIENT
import com.wHealth.activities.ui.doctor.DoctorFragment
import com.wHealth.activities.ui.clinics.ClinicFragment
import com.wHealth.di.activityScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_doctor_profile.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel


class  DoctorProfileActivity : BaseActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    private val viewModel: DoctorProfileViewModel by activityScope.viewModel(this)
    var docId:Int=0
    var clinicId:Int=0
    var cidd:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_profile)


        val clickedClinic = intent.getSerializableExtra("clickedClinic") as AppUser
        //val clickedDoctor = intent.getSerializableExtra("clickedDoctor") as? AppUser

        var user=sharedPreference.getCurrentUser()

        clinicName.text=clickedClinic.name
        clinicEmail.text=clickedClinic.email
        clinicAddress.text=clickedClinic.address
        clinicPhone.text=clickedClinic.phoneNo
        clinicRegistrationNo.text=clickedClinic.registrationNo

        docId=clickedClinic.id
        clinicId= user.id


         viewModel.cReqSuccessLiveData.observe(this, Observer { response->this
            if(response.status)
            {
                Toast.makeText(this,response.message, Toast.LENGTH_SHORT).show()

            }
            else
            {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }

        })

        approveDocRequest.visibility = GONE
        if(user.type== PATIENT)
        {
            //sendRequestToClinic.visibility = GONE

        }
        else if(user.type==CLINIC)
        {
            //sendRequestToClinic.visibility = GONE
            RegText.visibility = GONE
            clinicRegistrationNo.visibility = GONE
            approveDocRequest.visibility = VISIBLE

        }

        viewModel.cApproveDocSuccess.observe(this, Observer { response->this
            if(response.status)
            {
                Toast.makeText(this,response.message, Toast.LENGTH_SHORT).show()

            }
            else
            {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }

        })

        approveDocRequest.setOnClickListener({ moveToHome2() })
        //sendRequestToClinic.setOnClickListener { moveToHome() }
    }

    private fun moveToHome2()
    {
       viewModel.clinicApprovesDoc(clinicId,docId)
      //  val fragmentA = ClinicFragment()
        //supportFragmentManager.beginTransaction().replace(R.id.cf1,fragmentA).commit()
        //sendRequestToClinic.visibility = GONE
        //clinicEmail.visibility = GONE
//        val act = Intent(this, MainActivity::class.java)
//        startActivity(act)
        approveDocRequest.visibility = GONE
    }

    private fun moveToHome()
    {
        viewModel.reqToJoinClinic(docId,clinicId)
        val fragmentA = DoctorFragment()

        supportFragmentManager.beginTransaction().replace(R.id.cf1,fragmentA).commit()
        //sendRequestToClinic.visibility = GONE
        clinicEmail.visibility = GONE


    }



}