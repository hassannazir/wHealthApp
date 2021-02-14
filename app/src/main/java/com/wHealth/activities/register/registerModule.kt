package com.wHealth.activities.register

import androidx.annotation.RestrictTo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val registerModule = module {
    scope<RegisterActivity> {
        viewModel {
            RegisterViewModel(apiInterface = get())
        }
    }
}
