package com.wHealth.activities.ui.home
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
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.base.BaseFragment
import com.wHealth.adapters.ClinicAdapter
import com.wHealth.di.activityScope
import com.wHealth.di.fragmentScope
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel

//import com.wHealth.activities.R

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by fragmentScope.inject()
    private val clinicAdapter: ClinicAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getClinicsSuccessLiveData.observe(viewLifecycleOwner, Observer { response->
            if(response.status)
            {
                Toast.makeText(this.activity,response.message, Toast.LENGTH_SHORT).show()
                //clinicAdapter.setClinics(response.result)
                clinicRecyclerView.apply{
                    layoutManager= LinearLayoutManager(activity)
                    //adapter = clinicAdapter
                    ClinicAdapter(response.result)
                }

            }
            else
            {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getActiveClinics()

    }
}