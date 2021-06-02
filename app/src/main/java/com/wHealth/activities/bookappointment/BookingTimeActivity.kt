package com.wHealth.activities.bookappointment

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListViewModel
import com.wHealth.di.activityScope
import kotlinx.android.synthetic.main.activity_booking_time.*
import org.koin.androidx.viewmodel.scope.viewModel
import java.util.*

class BookingTimeActivity : BaseActivity() {
   private val viewModel: BookScheduleViewModel by activityScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_time)
        val clickeddoctor = intent.getSerializableExtra("clickedDoctor") as Int
        val clinic = intent.getSerializableExtra("Clinic") as Int
        val date = intent.getSerializableExtra("date") as CalendarDay
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
        }
        }
    }
