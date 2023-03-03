package com.andrew.starwars.presentation.ui.profile.theme_customization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andrew.starwars.data.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ThemeCustomizationViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ViewModel() {

    private val _currentThemeId = MutableLiveData<Int>()
    val currentThemeId: LiveData<Int> get() = _currentThemeId

    fun setCurrentTheme(currentThemeId: Int) {
        _currentThemeId.value = currentThemeId
    }

    fun saveTheme(themeId: Int) {
        sharedPreferences.saveUserToken(themeId)
    }

    fun getTheme(): Int {
        return sharedPreferences.getUserToken()
    }
}