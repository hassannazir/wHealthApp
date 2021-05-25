package com.wHealth.helper

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade


class AllDaysDisabledDecorator : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return true //decorate all days in calendar
    }

    override fun decorate(view: DayViewFacade) {
        view.setDaysDisabled(true) //disable all days
    }
}