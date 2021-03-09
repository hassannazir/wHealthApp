package com.wHealth.activities.ui.gallery

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.base.BaseFragment
import com.wHealth.activities.clinicrequest.ClinicProfileActivity
import com.wHealth.activities.ui.home.CellClickListener
import com.wHealth.activities.ui.home.GalleryViewModel
import com.wHealth.activities.ui.home.HomeViewModel
import com.wHealth.adapters.ClinicAdapter
import com.wHealth.di.fragmentScope
import com.wHealth.model.AppUser
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

//import com.wHealth.activities.R

class GalleryFragment : BaseFragment(), CellClickListener {

    private val viewModel: GalleryViewModel by fragmentScope.inject()
    lateinit var clinicAdapter: ClinicAdapter
    private  val sharedPreference: WHealthSharedPreference by inject()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clinicAdapter = ClinicAdapter(this)
        clinicRecyclerView.apply{
            layoutManager= LinearLayoutManager(activity)
            adapter = clinicAdapter

        }



        viewModel.getInactiveDocSuccessLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
                clinicAdapter.setClinics(response.result)

            } else {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getUsers()





    }
    override fun onCellClickListener(data:AppUser) {

        val act = Intent(context, ClinicProfileActivity::class.java)
        act.putExtra("clickedClinic", data)
        startActivity(act)

    }
}