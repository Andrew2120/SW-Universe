package com.andrew.starwars.presentation.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.andrew.starwars.MainActivity
import com.andrew.starwars.R
import com.andrew.starwars.databinding.ActivityAuthBinding
import com.andrew.starwars.presentation.MainViewModel
import com.andrew.starwars.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.internal.OnConnectionFailedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AuthActivity : AppCompatActivity(), OnConnectionFailedListener {
    private lateinit var binding: ActivityAuthBinding
    private val signInCode = 1
    var keepSplashOpen = true
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOpen
        }


        lifecycleScope
            .launchWhenCreated {
                mainViewModel
                    .userFlow.collectLatest {
                        when (it) {
                            MainViewModel.UserState.Loading -> {}
                            is MainViewModel.UserState.ShowError -> {}
                            is MainViewModel.UserState.Success -> {
                                if (it.data != null){
                                    Intent(applicationContext, MainActivity::class.java).apply {
                                        startActivity(this)
                                        finish()
                                    }
                                }
                                keepSplashOpen = false


                            }
                        }
                    }
            }

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.skipLogin.setOnClickListener {
            Intent(applicationContext, MainActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.signInButton.setOnClickListener {
            Constants.LoadingScreen.displayLoadingWithText(this,false)
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, signInCode)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == signInCode) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                FirebaseAuth.getInstance().signInWithCredential(
                    GoogleAuthProvider.getCredential(account.idToken, null)
                ).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Constants.LoadingScreen.hideLoading()
                        Intent(applicationContext, MainActivity::class.java).apply {
                            startActivity(this)
                            finish()
                        }
                    } else {
                        Constants.LoadingScreen.hideLoading()
                        Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: ApiException) {
                // Authentication failed
            }
        }

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }


}