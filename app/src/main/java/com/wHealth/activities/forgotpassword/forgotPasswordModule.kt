package com.wHealth.activities.forgotpassword

import androidx.annotation.RestrictTo
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val forgotPasswordModule = module {
    scope<ForgotPasswordActivity> {
        viewModel {
            ForgotPasswordViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
