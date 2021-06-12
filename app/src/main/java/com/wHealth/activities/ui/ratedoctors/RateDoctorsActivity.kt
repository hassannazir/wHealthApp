package com.wHealth.activities.ui.ratedoctors

import android.os.Bundle
import android.widget.Toast
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.di.activityScope
import com.wHealth.model.AppUser
import kotlinx.android.synthetic.main.activity_rate_doctors.*

class RateDoctorsActivity : BaseActivity() {

    private val viewModel: RateDoctorsViewModel by activityScope.inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_doctors)
        val clickeddoctor = intent.getSerializableExtra("clickedDoctor") as AppUser
        val clinic = intent.getSerializableExtra("Clinic") as Int
        doctorName.text="Dr. "+clickeddoctor.name
        viewModel.getInactiveDocSuccessLiveData.observe(this, androidx.lifecycle.Observer { response ->
            if (response.status)
            {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
        })
        submitReview.setOnClickListener {
            val noofstars: Int = doctorRating.getNumStars()
            val getrating: Float = doctorRating.getRating()
            viewModel.submitFeedback(clickeddoctor.id,clinic,getrating,doctorFeedback.text.toString())

        }

    }
}