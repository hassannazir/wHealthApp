package com.wHealth.activities.bookappointment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.wHealth.R
import com.wHealth.network.response.BookingSlot
import kotlinx.android.synthetic.main.doctor_item_view.view.*
import kotlinx.android.synthetic.main.view_booking_slots.view.*

class BookTimeAdapter (private val cellClickListener:  bookingslotclicklistener) : RecyclerView.Adapter<ClinicViewHolder>(){

    private val clinicList = mutableListOf<BookingSlot>()
    lateinit var context:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_booking_slots, parent, false)

        return ClinicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clinicList.size
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        holder.bind(clinicList[position])
        holder.itemView.setOnClickListener{
           // holder.itemView.slotCard.setCardBackgroundColor(Color.parseColor("#EFAFAF"));
            cellClickListener.onSlotClickListener(clinicList[position])
        }
    }

    fun setClinics(cList: List<BookingSlot>)
    {
        clinicList.addAll(cList)
        notifyDataSetChanged()
    }
    fun clearClinics()
    {
        clinicList.clear()
        notifyDataSetChanged()
    }
}
// Describes an item view and its place within the RecyclerView
class ClinicViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val slotStartTimeTextView: TextView = view.findViewById(R.id.slotStartTime)
    private val slotEndTimeTextView: TextView = view.findViewById(R.id.slotEndTime)
    private val slotCard: CardView = view.findViewById(R.id.slotCard)
    fun bind(appUser: BookingSlot) {
        slotStartTimeTextView.text = appUser.startTime
        slotEndTimeTextView.text=appUser.endTime
        if(appUser.status!=0)
        {
            slotCard.setCardBackgroundColor(Color.parseColor("#EFAFAF"));
        }


    }


}