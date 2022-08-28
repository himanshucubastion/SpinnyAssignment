package com.spinny.assignment.db

import android.content.Context
import android.content.SharedPreferences
import com.spinny.assignment.utilities.KEYS

class SessionConfig(val context: Context) {

    private var sharedPreferences: SharedPreferences? =
        context.getSharedPreferences(KEYS.SHARED_PREFERENCE, Context.MODE_PRIVATE)

    fun setLoginStatus(isLogin: Boolean, userId: Int) {
        sharedPreferences?.apply {
            edit().putBoolean(KEYS.IS_LOGIN, isLogin).putInt(KEYS.USER_ID, userId)
                .apply()
        }
    }

    fun getLoginStatus(): Boolean = sharedPreferences!!.getBoolean(KEYS.IS_LOGIN, false)

    fun getUserId(): Int = sharedPreferences!!.getInt(KEYS.USER_ID, 0)
}