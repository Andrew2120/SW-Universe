package com.andrew.starwars

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.andrew.starwars.databinding.ActivityMainBinding
import com.andrew.starwars.utils.Constants.Companion.PREFERENCES_NAME
import com.andrew.starwars.utils.Constants.Companion.THEME_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SetTheme {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun setTheme() {
        val pref = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        Log.d("TAG", "setTheme: " + pref.getInt(THEME_ID, 1))

        when (pref.getInt(THEME_ID, 1)) {
            1 -> setTheme(R.style.ThemeFirst)
            2 -> setTheme(R.style.ThemeSecond)
            3 -> setTheme(R.style.ThemeThird)
            4 -> setTheme(R.style.ThemeFourth)
            5 -> setTheme(R.style.ThemeFifth)
            6 -> setTheme(R.style.ThemeSixth)
        }
    }


}

interface SetTheme {
    fun setTheme()
}