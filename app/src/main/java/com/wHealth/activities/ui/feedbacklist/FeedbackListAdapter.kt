package com.wHealth.activities.ui.feedbacklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wHealth.R
import com.wHealth.network.response.feedback

class FeedbackListAdapter ( ) : RecyclerView.Adapter<ClinicViewHolder>(){

    private val feedbackcList = mutableListOf<feedback>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClinicViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_feedback_list, parent, false)

        return ClinicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feedbackcList.size
    }

    override fun onBindViewHolder(holder: ClinicViewHolder, position: Int) {
        holder.bind(feedbackcList[position])
    }
    fun setFeedbackList(cList: List<feedback>)
    {
        feedbackcList.addAll(cList)
        notifyDataSetChanged()
    }
}
// Describes an item view and its place within the RecyclerView
class ClinicViewHolder(view: View): RecyclerView.ViewHolder(view){
    private val patientNameTextView: TextView = view.findViewById(R.id.patientName)
    private val clinicNameTextView: TextView = view.findViewById(R.id.clinicName)
    private val ratingTextView: RatingBar = view.findViewById(R.id.rating)
    private val reviewTextView: TextView = view.findViewById(R.id.review)

    fun bind(appUser: feedback) {
        patientNameTextView.text = appUser.patientName
        clinicNameTextView.text=appUser.clinicName
        ratingTextView.setRating(appUser.rating);
        reviewTextView.text=appUser.review
    }


}