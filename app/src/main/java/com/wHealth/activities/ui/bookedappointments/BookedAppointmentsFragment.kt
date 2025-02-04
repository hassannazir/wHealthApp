package com.wHealth.activities.ui.bookedappointments

import android.app.AlertDialog
import android.content.Intent
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
import com.wHealth.activities.login.LoginActivity
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
                appointmentAdapter.setClinics(response.result,name)
            } else {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getAppointmentSuccessLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
                getlist()
            } else {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        getlist()
    }
     fun getlist(){
         val user=sharedPreference.getCurrentUser()

         if(user?.type == "Patient")
         {
             viewModel.getPatientAppointments()
         }
         else{
             if(name=="booked")
             {
                 pagetitle.text="Booked Appointments"
                 viewModel.getBookedAppointments()
             }
             else if(name=="pending"){
                 pagetitle.text="Pending Appointments"
                 viewModel.getPendingAppointments()
             }

         }

     }
    override fun onApproveClickListener(data: Booking) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Approve Appointment!")
        builder.setMessage("Are you sure you want to approve appointment?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            viewModel.approveRequest(data.appointmentId,true)
        }
        builder.setNegativeButton("No") { dialog, id ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()

    }

    override fun onCancelClickListener(data: Booking) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Cancel Appointment!")
        builder.setMessage("Are you sure you want to cancel appointment?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            viewModel.approveRequest(data.appointmentId,false)
        }
        builder.setNegativeButton("No") { dialog, id ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()

    }

}