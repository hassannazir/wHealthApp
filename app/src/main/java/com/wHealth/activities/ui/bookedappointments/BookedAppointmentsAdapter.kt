package com.wHealth.activities.ui.bookedappointments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wHealth.R
import com.wHealth.activities.bookappointment.AppointmentClickListener
import com.wHealth.activities.ui.allClinics.ClinicClickListener
import com.wHealth.model.AppUser
import com.wHealth.network.response.Booking
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.book_appointment_item_view.view.*
import kotlinx.android.synthetic.main.clinic_item_view.view.*

class BookedAppointmentsAdapter (
    private val sharedPreference: WHealthSharedPreference,
    private val cellClickListener: AppointmentClickListener
): RecyclerView.Adapter<BookedAppointmentsAdapter.ClinicViewHolder>(){
    val user=sharedPreference.getCurrentUser()

    var clinicList: List<Booking> = listOf()
    lateinit var stringName:String

    class ClinicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val patientnameTextView: TextView = itemView.findViewById(R.id.patientName)
        private val clinicnameTextView: TextView = itemView.findViewById(R.id.clinicName)
        private val bookingdateTextView: TextView = itemView.findViewById(R.id.bookingDate)
        private val starttimeTextView: TextView = itemView.findViewById(R.id.startTime)
        private val endtimeTextView: TextView = itemView.findViewById(R.id.endTime)

        fun bind(appUser: Booking) {
            patientnameTextView.text = "Patient Name: " +appUser.patientName
            clinicnameTextView.text="Clinic Name: "+appUser.clinicName
            bookingdateTextView.text="Booking Date: "+appUser.date?.take(10)
            starttimeTextView.text="Start Time: "+appUser.startTime?.take(5)
            endtimeTextView.text="End Time: "+appUser.endTime?.take(5)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_appointment_item_view, parent, false)

        return ClinicViewHolder(view)
    }
    override fun getItemCount(): Int {
        return clinicList.size
    }
    fun setClinics(cList: List<Booking>,name: String)
    {
        stringName=name
        clinicList = cList
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        holder.bind(clinicList[position])

        if(user.type=="Patient")
        {
            holder.itemView.approveAppointment.visibility=View.GONE
            holder.itemView.cancelAppointment.visibility=View.GONE
        }
        if(stringName=="booked")
        {
            holder.itemView.appointmentButtons.visibility=View.GONE
        }
        holder.itemView.approveAppointment.setOnClickListener {
            cellClickListener.onApproveClickListener(clinicList[position] as Booking)
        }
        holder.itemView.cancelAppointment.setOnClickListener {
            cellClickListener.onCancelClickListener(clinicList[position] as Booking)
        }


    }

}
