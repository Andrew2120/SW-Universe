package com.andrew.starwars.presentation.ui.species

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.andrew.starwars.R
import com.andrew.starwars.databinding.FragmentPersonBinding
import com.andrew.starwars.databinding.FragmentSpeciesBinding
import com.andrew.starwars.presentation.ui.person.PersonFragmentArgs
import com.andrew.starwars.utils.BaseFragment

class SpeciesFragment : BaseFragment<FragmentSpeciesBinding>(FragmentSpeciesBinding::inflate) {
    val args: SpeciesFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.species = args.species
    }

}