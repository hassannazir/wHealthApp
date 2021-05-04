package com.wHealth.activities.ui.clinicschedulelist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.base.BaseFragment
import com.wHealth.di.activityScope
import com.wHealth.di.fragmentScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_view_clinic_schedule.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel

//import com.wHealth.activities.R

class ClinicScheduleListActivity : BaseActivity(){

    private val viewModel: ClinicScheduleListViewModel by activityScope.viewModel(this)
    lateinit var clinicAdapter: ClinicScheduleListAdapter
    private  val sharedPreference: WHealthSharedPreference by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_clinic_schedule)


        val clickedClinic = intent.getSerializableExtra("clickedClinic") as AppUser
        //val Clinic = intent.getSerializableExtra("Clinic") as Int

        clinicAdapter = ClinicScheduleListAdapter()
        allClinicRecyclerView.layoutManager = LinearLayoutManager(this)
        allClinicRecyclerView.adapter = clinicAdapter
        viewModel.getInactiveDocSuccessLiveData.observe(this, Observer { response ->
            if (response.status) {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                clinicAdapter.setClinicsSchedule(response.result)
            } else {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
        })

        var docId= sharedPreference.getCurrentUser().id
        if(sharedPreference.getCurrentUser().type=="Patient")
        {
            //viewModel.getClinicSchedule(Clinic,clickedClinic.id)
        }
        else{
            viewModel.getClinicSchedule(docId,clickedClinic.id)
        }
    }
}