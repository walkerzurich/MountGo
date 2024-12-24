package com.example.baka.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "user_session"
        private const val IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_EMAIL = "email"
    }

    // Save session data
    fun createLoginSession(userId: String, userName: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_USER_NAME, userName)
        editor.putString(KEY_EMAIL, email)
        editor.apply()
    }

    // Check if user is logged in
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    // Get user data
    fun getUserDetails(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user["user_id"] = sharedPreferences.getString(KEY_USER_ID, null) ?: ""
        user["user_name"] = sharedPreferences.getString(KEY_USER_NAME, null) ?: ""
        user["email"] = sharedPreferences.getString(KEY_EMAIL, null) ?: ""
        return user
    }

    // Logout user
    fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
