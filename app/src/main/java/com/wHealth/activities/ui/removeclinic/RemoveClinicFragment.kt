package com.wHealth.activities.ui.removeclinic

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wHealth.R
import com.wHealth.activities.base.BaseFragment
import com.wHealth.activities.login.LoginActivity
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.fragment_remove_clinic.*
import org.koin.android.ext.android.inject

class RemoveClinicFragment : BaseFragment() {
    private  val sharedPreference: WHealthSharedPreference by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_remove_clinic, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        removeClinic.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Request of Removal from App")
            builder.setMessage("Are you sure you want to leave this application?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { dialogInterface, which ->
                sharedPreference.removeUserToken()
                sharedPreference.clearSharedPreference()
                Toast.makeText(context, "Successfully Logout.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, LoginActivity::class.java))
            }
            builder.setNegativeButton("No") { dialog, id ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.show()
        }
    }
}