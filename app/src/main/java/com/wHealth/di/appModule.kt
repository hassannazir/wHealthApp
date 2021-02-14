package com.wHealth.di

import ApiInterface
import android.app.Application
import android.content.ContentResolver
import android.preference.PreferenceManager
import android.util.Log
import com.bumptech.glide.RequestManager
import com.wHealth.BuildConfig
import com.wHealth.logger.LoggerImpl
import com.wHealth.logger.Logger
import com.wHealth.sharedpreferences.WHealthSharedPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
//import java.util.logging.Logger


// TODO Koin Use named parameters to make the "get()" stuff clearer
val appModule = module {
    single<ContentResolver> { get<Application>().contentResolver }
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
    single { androidContext().resources }
    single { WHealthSharedPreference(androidContext()) }



    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("RockNetwork", message)
            }
        })
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            // if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    // Network
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build())
            .build()
    }
    single { get<Retrofit>().create(ApiInterface::class.java) }
    single<Logger> { LoggerImpl() }


    //single { ImageUrlFormatter(get(), get()) }
   // single<RequestManager> { GlideApp.with(androidContext()) }
    //single { ImageLoader(get(), get(), get()) }
}
