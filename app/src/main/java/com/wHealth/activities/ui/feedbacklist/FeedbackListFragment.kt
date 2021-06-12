package com.wHealth.activities.ui.feedbacklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.wHealth.R
import com.wHealth.activities.base.BaseFragment
import com.wHealth.activities.ui.allClinics.AllClinicAdapter
import com.wHealth.di.fragmentScope
import kotlinx.android.synthetic.main.fragment_allclinic.*
import kotlinx.android.synthetic.main.fragment_feedback_list.*

class FeedbackListFragment : BaseFragment() {
    private val viewModel: FeedbackListViewModel by fragmentScope.inject()
    lateinit var feedbackAdapter: FeedbackListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feedback_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedbackAdapter = FeedbackListAdapter()
        feedbackRecyclerView.apply{
            layoutManager= LinearLayoutManager(activity)
            adapter = feedbackAdapter
        }
        viewModel.getFeedbackListSuccessLiveData.observe(viewLifecycleOwner, Observer { response ->
            if (response.status) {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
                feedbackAdapter.setFeedbackList(response.result)
            } else {
                Toast.makeText(this.activity, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getFeedbackList()


    }
}