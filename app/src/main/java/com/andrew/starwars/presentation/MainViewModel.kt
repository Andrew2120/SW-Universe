package com.andrew.starwars.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.starwars.R
import com.andrew.starwars.domain.use_cases.user.GetUserUseCase
import com.andrew.starwars.domain.use_cases.user.LogoutUserUseCase
import com.andrew.starwars.utils.DispatcherProvider
import com.andrew.starwars.utils.UiText
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {


    sealed class UserState {
        object Loading : UserState()
        data class Success(val data: FirebaseUser?) : UserState()
        data class ShowError(val message: UiText) : UserState()
    }

    private val _userFlow = MutableStateFlow<UserState>(UserState.Loading)
    val userFlow = _userFlow.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val planets = getUserUseCase()
                _userFlow.emit(UserState.Success(planets.first()))

            } catch (e: Exception) {
                if (e.message != null) {
                    _userFlow.emit(UserState.ShowError(UiText.DynamicString(e.message!!)))
                } else {
                    _userFlow.emit(UserState.ShowError(UiText.StringResource(R.string.somethingWentWrong)))
                }


            }
        }
    }

    fun logout() {
        viewModelScope.launch(dispatcherProvider.io) {
            logoutUserUseCase()

        }
    }

}