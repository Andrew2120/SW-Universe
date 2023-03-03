package com.andrew.starwars.presentation.ui.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.andrew.starwars.utils.DispatcherProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,

    ) : ViewModel() {
    private val _setupEvent = MutableSharedFlow<SocialAuthEvent>()
    val setupEvent: SharedFlow<SocialAuthEvent> = _setupEvent

    sealed class SocialAuthEvent {
        object IsLoading : SocialAuthEvent()
        data class RegisteredEvent(val user: Unit) : SocialAuthEvent()
        data class RegisteredEventError(val error: Exception) : SocialAuthEvent()
    }






}