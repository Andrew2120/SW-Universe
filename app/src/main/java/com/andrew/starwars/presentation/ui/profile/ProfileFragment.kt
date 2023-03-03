package com.andrew.starwars.presentation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andrew.starwars.databinding.FragmentProfileBinding
import com.andrew.starwars.presentation.MainViewModel
import com.andrew.starwars.presentation.ui.auth.AuthActivity
import com.andrew.starwars.utils.BaseFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut().apply {
                Intent(requireContext(), AuthActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().finish()
                }
            }

        }

        binding.themeSettingsContainer.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionNavigationProfileToThemeCustomizationFragment())
        }
        lifecycleScope.launchWhenCreated {
            mainViewModel.userFlow.collectLatest {
                when (it) {
                    MainViewModel.UserState.Loading -> {}
                    is MainViewModel.UserState.ShowError -> {}
                    is MainViewModel.UserState.Success -> {
                        binding.user = it.data
                    }
                }
            }
        }


    }

}