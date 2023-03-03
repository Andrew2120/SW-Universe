package com.andrew.starwars.presentation.ui.person

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.andrew.starwars.databinding.FragmentPersonBinding
import com.andrew.starwars.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

class PersonFragment : BaseFragment<FragmentPersonBinding>(FragmentPersonBinding::inflate) {
    private val args: PersonFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.person = args.person
    }

}