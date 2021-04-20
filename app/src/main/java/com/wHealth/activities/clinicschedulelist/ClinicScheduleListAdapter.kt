package com.wHealth.activities.ui.clinicschedulelist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.wHealth.R
import com.wHealth.activities.ui.doctor.CellClickListener
import com.wHealth.model.AppUser
import com.wHealth.network.response.clinicSchedule
import kotlinx.android.synthetic.main.clinic_item_view.view.*
import kotlinx.android.synthetic.main.clinic_schedule_item_view.view.*


class ClinicScheduleListAdapter( ) : RecyclerView.Adapter<ClinicViewHolder>(){

    private val clinicList = mutableListOf<clinicSchedule>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.clinic_schedule_item_view, parent, false)

        return ClinicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clinicList.size
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        if(clinicList[position].recurring==false)
        {
            holder.itemView.scheduleday.visibility=View.GONE
        }
        holder.bind(clinicList[position])

    }

    public interface onClickListener
    {
        fun onClick(au:AppUser)
    }

    fun setClinicsSchedule(cList: List<clinicSchedule>)
    {
        clinicList.addAll(cList)
        notifyDataSetChanged()
    }
}
// Describes an item view and its place within the RecyclerView
class ClinicViewHolder(view: View):RecyclerView.ViewHolder(view){
    private val clinicDateTextView: TextView = view.findViewById(R.id.scheduleDate)
    private val clinicTimeTextView: TextView = view.findViewById(R.id.scheduleTime)
    private val clinicDayTextView: TextView = view.findViewById(R.id.scheduleday)
    private val clinicSlotLengthTextView: TextView = view.findViewById(R.id.scheduleSlotLength)

    fun bind(appUser: clinicSchedule) {
        clinicDateTextView.text = appUser.startDate+" - "+appUser.endDate
        clinicTimeTextView.text=appUser.startTime +" - "+ appUser.endTime
        clinicDayTextView.text=appUser.day
        clinicSlotLengthTextView.text=appUser.slotLength.toString()
    }


}