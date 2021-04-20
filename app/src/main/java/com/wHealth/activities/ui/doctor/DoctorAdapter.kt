package com.wHealth.activities.ui.doctor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.wHealth.R
import com.wHealth.activities.forgotpassword.ForgotPasswordActivity
import com.wHealth.activities.ui.doctor.CellClickListener
import com.wHealth.model.AppUser
import kotlinx.android.synthetic.main.clinic_item_view.view.*


class DoctorAdapter( private val cellClickListener: CellClickListener) : RecyclerView.Adapter<ClinicViewHolder>(){

    private val clinicList = mutableListOf<AppUser>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.clinic_item_view, parent, false)

        return ClinicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clinicList.size
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        holder.bind(clinicList[position])
        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(clinicList[position])
        }
        holder.itemView.scheduleClinicTiming.setOnClickListener {
            cellClickListener.onScheduleTimingClick(clinicList[position])
        }
        holder.itemView.viewScheduleClinicTiming.setOnClickListener {
            cellClickListener.onViewScheduleTimingClick(clinicList[position])
        }
        }

    fun setClinics(cList: List<AppUser>)
    {
        clinicList.addAll(cList)
        notifyDataSetChanged()
    }
}
// Describes an item view and its place within the RecyclerView
class ClinicViewHolder(view: View):RecyclerView.ViewHolder(view){
    private val clinicNameTextView: TextView = view.findViewById(R.id.item_name)
    private val clinicPhoneTextView: TextView = view.findViewById(R.id.item_phone)
    private val clinicAddressTextView: TextView = view.findViewById(R.id.item_Address)

    fun bind(appUser: AppUser) {
        clinicNameTextView.text = appUser.name
        clinicPhoneTextView.text=appUser.phoneNo
        clinicAddressTextView.text=appUser.address
    }


}