package com.wHealth.activities.ui.gallery

import androidx.annotation.RestrictTo
import com.wHealth.activities.ui.home.GalleryViewModel
import com.wHealth.adapters.ClinicAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val galleryModule = module {
    scope<GalleryFragment> {
        viewModel {
            GalleryViewModel(apiInterface = get(), sharedPreference=get())
        }
    }
}
