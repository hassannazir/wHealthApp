package com.wHealth.activities.clinicdoctors

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
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.doctor_item_view.view.*


class ClinicDoctorAdapter( private val cellClickListener: ClinicDoctorClickListener,private val sharedPreference: WHealthSharedPreference,private  val clinic:Int) : RecyclerView.Adapter<ClinicViewHolder>(){

    private val clinicList = mutableListOf<AppUser>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.doctor_item_view, parent, false)

        return ClinicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clinicList.size
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        holder.bind(clinicList[position])

        if(sharedPreference.getCurrentUser().type=="Patient")
        {
            holder.itemView.viewDoctorSchedule.visibility=View.VISIBLE
            holder.itemView.rateDoctor.visibility=View.VISIBLE
        }
        holder.itemView.viewDoctorSchedule.setOnClickListener {
            cellClickListener.onClinicdoctorClickListener(clinicList[position],clinic)
        }
        holder.itemView.rateDoctor.setOnClickListener{
            cellClickListener.onratedoctorClickListener(clinicList[position],clinic)
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