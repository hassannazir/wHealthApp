package com.wHealth.activities.ui.feedbacklist

import com.wHealth.activities.ui.doctor.DoctorFragment
import com.wHealth.activities.ui.doctor.DoctorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val FeedbacklistModule = module {
    scope<FeedbackListFragment> {
        viewModel {
            FeedbackListViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
