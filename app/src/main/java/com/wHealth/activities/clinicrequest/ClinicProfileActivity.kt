package com.wHealth.activities.clinicrequest

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.register.RegisterViewModel.Companion.CLINIC
import com.wHealth.activities.register.RegisterViewModel.Companion.PATIENT
import com.wHealth.activities.ui.doctor.DoctorFragment
import com.wHealth.activities.ui.clinics.ClinicFragment
import com.wHealth.di.activityScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_clinic_profile.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel


class  ClinicProfileActivity : BaseActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    private val viewModel: ClinicRequestViewModel by activityScope.viewModel(this)
    var docId:Int=0
    var clinicId:Int=0
    var cidd:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic_profile)


        val clickedClinic = intent.getSerializableExtra("clickedClinic") as AppUser
        val approved= intent.getSerializableExtra("approved") as Int
        //val clickedDoctor = intent.getSerializableExtra("clickedDoctor") as? AppUser

        var user=sharedPreference.getCurrentUser()

        clinicName.text=clickedClinic.name
        clinicEmail.text=clickedClinic.email
        clinicAddress.text=clickedClinic.address
        clinicPhone.text=clickedClinic.phoneNo
        clinicRegistrationNo.text=clickedClinic.registrationNo

        docId=clickedClinic.id
        clinicId= user.id
        if(approved==0)
        {
            sendRequestToClinic.visibility=View.VISIBLE
        }
        else{
            sendRequestToClinic.visibility=View.GONE
        }


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

        //approveDocRequest.visibility = GONE
        if(user.type== PATIENT)
        {
            sendRequestToClinic.visibility = GONE

        }
        else if(user.type==CLINIC)
        {
            sendRequestToClinic.visibility = GONE
            RegText.visibility = GONE
            clinicRegistrationNo.visibility = GONE

            //approveDocRequest.visibility = VISIBLE

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

        //approveDocRequest.setOnClickListener({ moveToHome2() })
        sendRequestToClinic.setOnClickListener { moveToHome() }
    }

    private fun moveToHome2()
    {
       viewModel.clinicApprovesDoc(clinicId,docId)
        val fragmentA = ClinicFragment()

        supportFragmentManager.beginTransaction().replace(R.id.cf1,fragmentA).commit()
        sendRequestToClinic.visibility = GONE
        clinicEmail.visibility = GONE
        //approveDocRequest.visibility = GONE


    }

    private fun moveToHome()
    {
        viewModel.reqToJoinClinic(docId,clinicId)

    }



}