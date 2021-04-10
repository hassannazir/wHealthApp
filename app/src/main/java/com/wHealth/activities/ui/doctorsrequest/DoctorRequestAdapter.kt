package com.wHealth.activities.ui.doctorsrequest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.wHealth.R
import com.wHealth.activities.forgotpassword.ForgotPasswordActivity
import com.wHealth.model.AppUser
import kotlinx.android.synthetic.main.doctor_item_view.view.*


class DoctorRequestAdapter(
        private val cellClickListener: CellClickListener
) : RecyclerView.Adapter<DoctorRequestAdapter.ClinicViewHolder>(){

    var clinicList: List<AppUser> = listOf()

    class ClinicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.doctor_item_view, parent, false)

        return ClinicViewHolder(view)
    }
    override fun getItemCount(): Int {
        return clinicList.size
    }
    fun setClinics(cList: List<AppUser>)
    {
        clinicList = cList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        val appUser = clinicList[position]
        holder.itemView.apply {

            holder.itemView.item_name.text = appUser.name
            holder.itemView.item_phone.text = appUser.phoneNo
            holder.itemView.item_Address.text = appUser.address
        }
    }

}