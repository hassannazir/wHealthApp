package com.wHealth.activities.ui.doctorsrequest

import android.R.attr.data
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
import com.wHealth.activities.doctorprofile.DoctorProfileActivity
import com.wHealth.di.fragmentScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.fragment_doctorrequest.*
import org.koin.android.ext.android.inject


//import com.wHealth.activities.R

interface CellClickListener {
    fun onCellClickListener(data: AppUser)
}

class DoctorRequestFragment : BaseFragment(),CellClickListener{



    private val viewModel: DoctorRequestViewModel by fragmentScope.inject()
    lateinit var doctorAdapter: DoctorRequestAdapter
    private  val sharedPreference: WHealthSharedPreference by inject()
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_doctorrequest, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doctorAdapter = DoctorRequestAdapter(this)
        doctorRequestRecyclerView.removeAllViewsInLayout()
        doctorRequestRecyclerView.apply{
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
            viewModel.getPendingDoctorsRequest()
    }

    override fun onCellClickListener(data:AppUser) {
        val act = Intent(context, DoctorProfileActivity::class.java)
        act.putExtra("clickedClinic", data)
        startActivity(act)

    }
}