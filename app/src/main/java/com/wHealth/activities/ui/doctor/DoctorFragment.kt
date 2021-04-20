package com.wHealth.activities.ui.doctor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.clinicrequest.ClinicProfileActivity
import com.wHealth.activities.base.BaseFragment
import com.wHealth.activities.clinicschedule.ClinicScheduleActivity
import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListActivity
import com.wHealth.di.fragmentScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.fragment_doctor.*
import org.koin.android.ext.android.inject

//import com.wHealth.activities.R

interface CellClickListener {
    fun onCellClickListener(data: AppUser)
    fun onScheduleTimingClick(data: AppUser)
    fun onViewScheduleTimingClick(data: AppUser)
}

class DoctorFragment : BaseFragment(),CellClickListener{



    private val viewModel: DoctorViewModel by fragmentScope.inject()
    lateinit var doctorAdapter: DoctorAdapter
    private  val sharedPreference: WHealthSharedPreference by inject()
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_doctor, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorAdapter = DoctorAdapter(this)
        workingClinicRecyclerView.apply{
            layoutManager= LinearLayoutManager(activity)
            adapter = doctorAdapter
        }

            viewModel.getClinicsSuccessLiveData.observe(viewLifecycleOwner, Observer { response ->
                if (response.status) {
                    Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
                    doctorAdapter.setClinics(response.result)

                } else {
                    Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
                }
            })
            viewModel.getUsers()

    }

    override fun onCellClickListener(data:AppUser) {
        val act = Intent(context, ClinicProfileActivity::class.java)
        act.putExtra("clickedClinic", data)
        act.putExtra("approved", 1)
        startActivity(act)

    }

    override fun onScheduleTimingClick(data: AppUser) {
        val act = Intent(context, ClinicScheduleActivity::class.java)
        act.putExtra("clickedClinic", data)
        startActivity(act)
    }

    override fun onViewScheduleTimingClick(data: AppUser) {
        val act = Intent(context, ClinicScheduleListActivity::class.java)
        act.putExtra("clickedClinic", data)
        startActivity(act)
    }
}