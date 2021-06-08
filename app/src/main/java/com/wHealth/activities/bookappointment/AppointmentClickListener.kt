package com.wHealth.activities.bookappointment

import com.wHealth.network.response.Booking

interface AppointmentClickListener {
    fun onApproveClickListener(data: Booking)
    fun onCancelClickListener(data: Booking)
}