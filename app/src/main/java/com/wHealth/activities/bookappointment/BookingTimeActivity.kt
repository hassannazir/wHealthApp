package com.wHealth.activities.bookappointment

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.di.activityScope
import com.wHealth.network.response.BookingSlot
import kotlinx.android.synthetic.main.activity_booking_time.*
import org.koin.androidx.viewmodel.scope.viewModel
import java.text.SimpleDateFormat
import java.util.*

class BookingTimeActivity : BaseActivity(),bookingslotclicklistener {
   private val viewModel: BookScheduleViewModel by activityScope.viewModel(this)
    lateinit var slotAdapter: BookTimeAdapter
    var clickeddoctor:Int = 0
    var clinic:Int = 0
    lateinit var dateAppointment:Date
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_time)
        clickeddoctor = intent.getSerializableExtra("clickedDoctor") as Int
        clinic = intent.getSerializableExtra("Clinic") as Int
        dateAppointment = intent.getSerializableExtra("date") as Date
        slotAdapter = BookTimeAdapter(this)
        bookingSlots.apply{
            layoutManager= LinearLayoutManager(context)
            adapter = slotAdapter
        }
        viewModel.clinicScheduleSuccessLiveData.observe(this, androidx.lifecycle.Observer { response ->
            if (response.status) {

                val pattern = "yyyy-MM-d 00:00"
                val simpleDateFormat = SimpleDateFormat(pattern)
                val date: String = simpleDateFormat.format(dateAppointment)
               slotAdapter.clearClinics()
                viewModel.GetSLots(clickeddoctor,clinic,date)
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.bookingSlotSuccessLiveData.observe(this, androidx.lifecycle.Observer { response ->
            if (response.status) {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                slotAdapter.setClinics(response.result)
            } else {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        val pattern = "yyyy-MM-d 00:00"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(dateAppointment)
        viewModel.GetSLots(clickeddoctor,clinic,date)
        }

    override fun onSlotClickListener(data: BookingSlot) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Book Slot")
        builder.setMessage("Are you sure you want to book slot for this doctor?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { dialogInterface, which ->
            val startTime=data.startTime
            val endTime=data.endTime
            viewModel.booktiming(clickeddoctor,clinic,1,startTime,endTime,data.date)
        }
        builder.setNegativeButton("No") { dialog, id ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()

    }
}
