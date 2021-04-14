package com.wHealth.activities.clinicschedule

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.di.activityScope
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_clinic_schedule.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel
import java.util.*


class  ClinicScheduleActivity : BaseActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    private val viewModel: ClinicScheduleViewModel by activityScope.viewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clinic_schedule)
        radio_recurring.setOnClickListener {
            if(radio_recurring.isChecked)
            {

            }
        }
        edt_start_date.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener {
                val cldr: Calendar = Calendar.getInstance()
                val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                val month: Int = cldr.get(Calendar.MONTH)
                val year: Int = cldr.get(Calendar.YEAR)
                val picker = DatePickerDialog(
                        this@ClinicScheduleActivity, { view, year, monthOfYear, dayOfMonth ->
                    edt_start_date.setText(
                            dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                    )
                }, year, month, day
                )
                picker.setTitle("Select Date")
                picker.show()
            }
        }
        edt_end_date.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener {
                val cldr: Calendar = Calendar.getInstance()
                val day: Int = cldr.get(Calendar.DAY_OF_MONTH)
                val month: Int = cldr.get(Calendar.MONTH)
                val year: Int = cldr.get(Calendar.YEAR)
                val picker = DatePickerDialog(
                        this@ClinicScheduleActivity, { view, year, monthOfYear, dayOfMonth ->
                    edt_end_date.setText(
                            dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                    )
                }, year, month, day
                )
                picker.setTitle("Select Date")
                picker.show()
            }
        }
        edt_start_time.apply {
            inputType = InputType.TYPE_NULL
            setOnClickListener {
                // TODO Auto-generated method stub
                val mcurrentTime = Calendar.getInstance()
                val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
                val minute = mcurrentTime[Calendar.MINUTE]
                val mTimePicker: TimePickerDialog
                mTimePicker = TimePickerDialog(this@ClinicScheduleActivity, OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    edt_start_time.setText("$selectedHour:$selectedMinute") }, hour, minute,false) //Yes 24 hour time
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
                mTimePicker = TimePickerDialog(this@ClinicScheduleActivity, OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    edt_start_time.setText("$selectedHour:$selectedMinute") }, hour, minute,false) //Yes 24 hour time
                mTimePicker.show()
            }
        }
    }
}