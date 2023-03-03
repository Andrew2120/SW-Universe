package com.andrew.starwars.presentation.ui.movie

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.andrew.starwars.databinding.FragmentMovieViewBinding
import com.andrew.starwars.utils.BaseFragment

class MovieFragment : BaseFragment<FragmentMovieViewBinding>(FragmentMovieViewBinding::inflate) {
    private val args: MovieFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movie = args.movie
    }

}