package com.wHealth.activities.ui.removeclinic

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wHealth.R
import kotlinx.android.synthetic.main.fragment_remove_clinic.*

class RemoveClinicFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
            }
            builder.setNegativeButton("No") { dialog, id ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(true)
            alertDialog.show()
        }
    }
}