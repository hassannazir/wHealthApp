package com.wHealth.activities.updateprofile

import com.wHealth.activities.updateprofile.UpdateProfileViewModel
import com.wHealth.activities.updateprofile.UpdateProfileActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val updateProfileModule = module {
    scope<UpdateProfileActivity> {
        viewModel {
            UpdateProfileViewModel(apiInterface = get())
        }
    }
}
