package com.wHealth.activities.bookappointment

import android.os.Bundle
import android.widget.Toast
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.ui.clinicschedulelist.ClinicScheduleListViewModel
import com.wHealth.di.activityScope
import com.wHealth.helper.AllDaysDisabledDecorator
import com.wHealth.helper.AvailableDaysDecorator
import com.wHealth.model.AppUser
import kotlinx.android.synthetic.main.activity_book_appointment.*
import org.koin.androidx.viewmodel.scope.viewModel
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class BookAppointmentActivity : BaseActivity(){
    private val viewModel: ClinicScheduleListViewModel by activityScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)
        val clickedClinic = intent.getSerializableExtra("clickedDoctor") as AppUser
        val doctor = intent.getSerializableExtra("Clinic") as Int
        viewModel.getClinicSchedule(doctor,clickedClinic.id)
//        viewModel.getInactiveDocSuccessLiveData.observe(this,androidx.lifecycle.Observer{response ->
//            if (response.status) {
//                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
//                val dates = getDates(response.result.first().startDate, response.result.first().endDate)
//                val enabledDates: ArrayList<CalendarDay> = ArrayList()
//                if (dates != null) {
//                    for(date in dates) {
//                        enabledDates.add(CalendarDay(date));
//                    }
//                }
//                calendarView.addDecorator(
//                    AvailableDaysDecorator(
//                        R.color.teal_700,
//                        enabledDates
//                    )
//                )
//            } else {
//                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
//            }
//        })
        //calendarView.addDecorator(AllDaysDisabledDecorator())
        //calendarView.addDecorator(AvailableDaysDecorator(R.color.teal_700, days))

    }
    private fun getDates(
        dateString1: String?,
        dateString2: String?
    ): List<Date>? {
        val dates = ArrayList<Date>()
        val df1: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        var date1: Date? = null
        var date2: Date? = null
        try {
            date1 = df1.parse(dateString1)
            date2 = df1.parse(dateString2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val cal1 = Calendar.getInstance()
        cal1.time = date1
        val cal2 = Calendar.getInstance()
        cal2.time = date2
        while (!cal1.after(cal2)) {
            dates.add(cal1.time)
            cal1.add(Calendar.DATE, 1)
        }
        return dates
    }
}