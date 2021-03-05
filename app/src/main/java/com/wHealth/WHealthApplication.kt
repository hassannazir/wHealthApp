package com.wHealth

import android.app.Application
import com.wHealth.activities.forgotpassword.forgotPasswordModule
import com.wHealth.activities.register.registerModule
import com.wHealth.activities.login.loginModule
import com.wHealth.activities.ui.home.homeModule
import com.wHealth.activities.updateprofile.updateProfileModule
import com.wHealth.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WHealthApplication : Application() {

    private val globalModules =
        listOf(
            appModule,
            registerModule,
            loginModule,
                forgotPasswordModule,
            updateProfileModule,
            homeModule

        )

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@WHealthApplication)
            modules(globalModules)
        }
    }
}
