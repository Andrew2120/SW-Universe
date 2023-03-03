package com.andrew.starwars.data

import android.content.Context
import com.andrew.starwars.utils.Constants.Companion.PREFERENCES_NAME
import com.andrew.starwars.utils.Constants.Companion.THEME_ID

class SharedPreferences(val context: Context) {

    private var sharedPreferences: android.content.SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)


    fun saveUserToken(theme: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(THEME_ID, theme)
        editor.apply()
    }


    fun getUserToken(): Int {
        return sharedPreferences.getInt(THEME_ID, 1)
    }


}