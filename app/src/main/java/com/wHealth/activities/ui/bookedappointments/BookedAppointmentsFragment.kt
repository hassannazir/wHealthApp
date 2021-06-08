package com.wHealth.activities.ui.bookedappointments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.base.BaseFragment
import com.wHealth.activities.bookappointment.AppointmentClickListener
import com.wHealth.di.fragmentScope
import com.wHealth.network.response.Booking
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.booked_appointments_fragment.*

import org.koin.android.ext.android.inject

class BookedAppointmentsFragment : BaseFragment() , AppointmentClickListener {
    private val viewModel: BookedAppointmentsViewModel by fragmentScope.inject()
    lateinit var appointmentAdapter: BookedAppointmentsAdapter
    var name=""
    private  val sharedPreference: WHealthSharedPreference by inject()
    companion object {
        fun newInstance() = BookedAppointmentsFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        name= getArguments()?.getString("name").toString()
        return inflater.inflate(R.layout.booked_appointments_fragment, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appointmentAdapter = BookedAppointmentsAdapter(sharedPreference,this)
        bookedAppointmentsRecyclerView.apply{
            layoutManager= LinearLayoutManager(activity)
            adapter = appointmentAdapter
        }
        viewModel.getInactiveDocSuccessLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
                appointmentAdapter.setClinics(response.result)
            } else {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        val user=sharedPreference.getCurrentUser()

        if(user?.type == "Patient")
        {
            viewModel.getPatientAppointments()
        }
        else{
            if(name!=null)
            {
                viewModel.getBookedAppointments()
            }
            else{
                viewModel.getAppointments()
            }

        }

    }

    override fun onApproveClickListener(data: Booking) {
        viewModel.approveRequest(data.appointmentId,true)
    }

    override fun onCancelClickListener(data: Booking) {
        viewModel.approveRequest(data.appointmentId,false)
    }

}