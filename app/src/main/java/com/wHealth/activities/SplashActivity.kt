package com.wHealth.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.wHealth.R
import com.wHealth.activities.login.LoginActivity
import com.wHealth.sharedpreferences.WHealthSharedPreference
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    // Splash screen timer
    private val SPLASH_TIME_OUT = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {

                if(!sharedPreference.isUserLoggedIn())
                {
                    val i = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }
                else {
                    val j = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(j)
                    finish()
                }


            }, SPLASH_TIME_OUT)
    }
}