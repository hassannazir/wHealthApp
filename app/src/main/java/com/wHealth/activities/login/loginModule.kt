package com.wHealth.activities.login

import androidx.annotation.RestrictTo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val loginModule = module {
    scope<LoginActivity> {
        viewModel {
            LoginViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
