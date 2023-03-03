package com.andrew.starwars.presentation.ui.planet

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.andrew.starwars.databinding.FragmentPlanetBinding
import com.andrew.starwars.utils.BaseFragment

class PlanetFragment : BaseFragment<FragmentPlanetBinding>(FragmentPlanetBinding::inflate) {
    val args: PlanetFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.planet = args.planet
    }

}