package com.wHealth.activities.ui.allClinics

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
import kotlinx.android.synthetic.main.clinic_item_view.view.*


class AllClinicAdapter(private val sharedPreference: WHealthSharedPreference, private val cellClickListener:ClinicClickListener) : RecyclerView.Adapter<ClinicViewHolder>(){

    private var clinicList:List<Any> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.clinic_item_view, parent, false)

        return ClinicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clinicList.size
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        holder.bind(clinicList[position] as AppUser)
        holder.itemView.setOnClickListener {
            cellClickListener.onClinicClickListener(clinicList[position] as AppUser)
        }
        val user=sharedPreference.getCurrentUser()
        if(user.type=="Patient")
        {
            holder.itemView.viewAvailableDoctor.visibility=View.VISIBLE
        }
        holder.itemView.viewAvailableDoctor.setOnClickListener {
            cellClickListener.onAvailableDoctorClickListener(clinicList[position] as AppUser)
        }
        holder.itemView.scheduleClinicTiming.visibility=View.GONE
        holder.itemView.viewScheduleClinicTiming.visibility=View.GONE

    }

    public interface onClickListener
    {
        fun onClick(au:AppUser)
    }

    fun setClinics(list: List<Any>)
    {
        clinicList=list
        notifyDataSetChanged()
    }

    fun getClinics():List<Any>
    {
        return clinicList
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