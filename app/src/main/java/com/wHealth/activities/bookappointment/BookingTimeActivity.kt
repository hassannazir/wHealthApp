package com.wHealth.activities.bookappointment

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.di.activityScope
import kotlinx.android.synthetic.main.activity_booking_time.*
import org.koin.androidx.viewmodel.scope.viewModel
import java.text.SimpleDateFormat
import java.util.*

class BookingTimeActivity : BaseActivity() {
   private val viewModel: BookScheduleViewModel by activityScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_time)
        val clickeddoctor = intent.getSerializableExtra("clickedDoctor") as Int
        val clinic = intent.getSerializableExtra("Clinic") as Int
        val dateAppointment = intent.getSerializableExtra("date") as Date
        edt_start_time.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener {
                // TODO Auto-generated method stub
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
                val minute = mcurrentTime[Calendar.MINUTE]
                val second = mcurrentTime[Calendar.SECOND]
                val mTimePicker: TimePickerDialog
                mTimePicker = TimePickerDialog(this@BookingTimeActivity,
                    TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                        edt_start_time.setText("$selectedHour:$selectedMinute")
                    }, hour, minute,false) //Yes 24 hour time
                mTimePicker.show()
            }
        }
        edt_end_time.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener {
                // TODO Auto-generated method stub
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
                val minute = mcurrentTime[Calendar.MINUTE]
                val mTimePicker: TimePickerDialog
                mTimePicker = TimePickerDialog(this@BookingTimeActivity,
                    TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                        edt_end_time.setText("$selectedHour:$selectedMinute")
                    }, hour, minute,false) //Yes 24 hour time
                mTimePicker.show()
            }
        }
        booktime.setOnClickListener {
            Toast.makeText(this, "Appointment is submitted for Approval.", Toast.LENGTH_SHORT).show()
            val startTime=edt_start_time.text.toString()+":00"
            val endTime=edt_end_time.text.toString()+":00"
            val pattern = "yyyy-MM-d 00:00"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val date: String = simpleDateFormat.format(dateAppointment)
            viewModel.booktiming(clickeddoctor,clinic,1,startTime,endTime,date,29)
        }
        viewModel.clinicScheduleSuccessLiveData.observe(this, androidx.lifecycle.Observer { response ->
            if (response.status) {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                //doctorAdapter.setClinics(response.result)
            } else {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        }
    }
