package com.wHealth.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.wHealth.model.AppUser

class WHealthSharedPreference(context:Context){

    private val sharedPref: SharedPreferences

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun clearSharedPreference() {
        sharedPref.edit().clear().apply()
    }

    fun saveToken(token: String?) {
        sharedPref.edit().putString(USER_TOKEN, token).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPref.contains(USER_TOKEN)
    }

    fun saveUser(data: AppUser?) {
        sharedPref.edit().putString(APP_USER, Gson().toJson(data)).apply()
    }

    fun getUserToken(): String? {
        return sharedPref.getString(USER_TOKEN, null)
    }

    fun getCurrentUser(): AppUser {
        val userString = sharedPref.getString(APP_USER, null)
        return  Gson().fromJson(userString, AppUser::class.java)
    }

    fun removeUserToken() {
        sharedPref.edit().remove(APP_USER).apply()
        sharedPref.edit().remove(USER_TOKEN).apply()
    }




    companion object {
        const val PREFS_NAME = "wHealth_Pref"
        const val USER_TOKEN = "user_token"
        const val APP_USER = "app_user"

    }


}