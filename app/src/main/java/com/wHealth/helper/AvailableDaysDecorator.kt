package com.wHealth.helper

import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan


class AvailableDaysDecorator internal constructor(
    private val color: Int,
    dates: Collection<CalendarDay?>?
) :
    DayViewDecorator {
    private val dates: HashSet<CalendarDay?>
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day) //decorate only available days
    }

    override fun decorate(view: DayViewFacade) {
        view.setDaysDisabled(false) ///important to enable day
        view.addSpan(DotSpan(5F, color)) //adds small dot to bottom of day number text
    }

    init {
        this.dates = HashSet(dates)
    }
}