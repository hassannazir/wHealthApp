package com.wHealth.activities.ui.allClinics

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.base.BaseFragment
import com.wHealth.activities.clinicdoctors.ClinicDoctorListActivity
import com.wHealth.activities.clinicrequest.ClinicProfileActivity
import com.wHealth.di.fragmentScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.fragment_allclinic.*
import org.koin.android.ext.android.inject


//import com.wHealth.activities.R

class AllClinicFragment : BaseFragment(), ClinicClickListener {

    private val viewModel: AllClinicViewModel by fragmentScope.inject()
    lateinit var clinicAdapter: AllClinicAdapter
    private  val sharedPreference: WHealthSharedPreference by inject()
    private lateinit var clinicList: List<AppUser>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_allclinic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clinicAdapter = AllClinicAdapter(sharedPreference,this)
        allClinicRecyclerView.apply{
            layoutManager= LinearLayoutManager(activity)
            adapter = clinicAdapter

        }
        viewModel.getInactiveDocSuccessLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
                clinicAdapter.setClinics(response.result)
                clinicList=response.result
            } else {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getUsers()
//        btnSearchClinic.setOnClickListener({
//            var searchquery=searchClinic.
//        })
    }


    override fun onClinicClickListener(data: AppUser) {
        val act = Intent(context, ClinicProfileActivity::class.java)
        act.putExtra("clickedClinic", data)
        act.putExtra("approved", 0)
        startActivity(act)
    }
    override fun onAvailableDoctorClickListener(data: AppUser) {
        val act = Intent(context, ClinicDoctorListActivity::class.java)
        act.putExtra("clickedClinic", data)
        act.putExtra("approved", 0)
        startActivity(act)

    }
}