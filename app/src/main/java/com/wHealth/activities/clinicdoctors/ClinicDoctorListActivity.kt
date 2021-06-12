package com.wHealth.activities.clinicdoctors

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.bookappointment.BookAppointmentActivity
import com.wHealth.activities.ui.clinics.ClinicViewModel
import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListActivity
import com.wHealth.activities.ui.ratedoctors.RateDoctorsActivity
import com.wHealth.di.activityScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_clinic.*
import kotlinx.android.synthetic.main.doctor_item_view.*
import org.koin.android.ext.android.inject


class ClinicDoctorListActivity : BaseActivity(), ClinicDoctorClickListener {

    private val viewModel: ClinicViewModel by activityScope.inject()
    lateinit var clinicAdapter: ClinicDoctorAdapter
    private  val sharedPreference: WHealthSharedPreference by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic)
        val clickedClinic = intent.getSerializableExtra("clickedClinic") as AppUser
        clinicAdapter = ClinicDoctorAdapter(this,sharedPreference,clickedClinic.id)
        clinicRecyclerView.apply{
            layoutManager= LinearLayoutManager(this@ClinicDoctorListActivity)
            adapter = clinicAdapter
        }
       viewModel.getInactiveDocSuccessLiveData.observe(this, androidx.lifecycle.Observer { response ->
            if (response.status) {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                clinicAdapter.setClinics(response.result)

            } else {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
        })

        var cId= sharedPreference.getCurrentUser().id
        if(sharedPreference.getCurrentUser().type=="Patient")
        {
            viewModel.getUsers(clickedClinic.id)
        }
        else{
            viewModel.getUsers(cId)
        }

    }

    override fun onClinicdoctorClickListener(data: AppUser,clinic:Int) {
        val act = Intent(this, BookAppointmentActivity::class.java)
        act.putExtra("clickedDoctor", data)
        act.putExtra("Clinic", clinic)
        startActivity(act)
    }

    override fun onratedoctorClickListener(data: AppUser, clinic: Int) {
        val act = Intent(this, RateDoctorsActivity::class.java)
        act.putExtra("clickedDoctor", data)
        act.putExtra("Clinic", clinic)
        startActivity(act)
    }
}