package com.wHealth.activities.bookappointment

import com.wHealth.network.response.BookingSlot

interface bookingslotclicklistener {
    fun onSlotClickListener(data: BookingSlot)
}