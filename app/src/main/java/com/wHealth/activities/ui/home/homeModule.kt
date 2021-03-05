package com.wHealth.activities.ui.home

import androidx.annotation.RestrictTo
import com.wHealth.adapters.ClinicAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeModule = module {
    scope<HomeFragment> {
        viewModel {
            HomeViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
