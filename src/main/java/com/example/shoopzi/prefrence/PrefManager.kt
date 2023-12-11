package com.example.shoopzi.prefrence
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
//import android.content.SharedPreferences.Editor
import com.example.shoopzi.Models.User
import com.google.gson.Gson

import java.lang.Exception

class PrefManager(context: Context) {

    private val PREF_NAME = "com.example.shoopzi"
    private val KEY_ONBOARDING = "on_boarding"
    private val KEY_LOGIN = "login"
    private val KEY_USER = "user"

    private var preferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor: Editor = preferences.edit()


    fun updateOnBoardingStatus(status: Boolean) {
        editor.putBoolean(KEY_ONBOARDING, status)
        editor.commit()
    }

    fun getOnBoardingStatus(): Boolean {
        return preferences.getBoolean(KEY_ONBOARDING, false)
    }

    fun updateLoginStatus(status: Boolean) {
        editor.putBoolean(KEY_LOGIN, status)
        editor.commit()
    }

    fun getLoginStatus(): Boolean {
        return preferences.getBoolean(KEY_LOGIN, false)
    }

    fun updateUserCredential(user: User) {

        try {
            var gson = Gson()
            var jsonString = gson.toJson(user)
            editor.putString(KEY_USER, jsonString)
            editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUserCredential(): User? {

        var user: User? = null
        var json = preferences.getString(KEY_USER, null)
        if (json != null) {
            var gson = Gson()
            user = gson.fromJson(json, User::class.java)
        }
        return user
    }

}