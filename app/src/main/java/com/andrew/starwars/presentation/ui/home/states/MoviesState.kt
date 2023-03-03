package com.andrew.starwars.presentation.ui.home.states

import com.andrew.starwars.domain.model.MovieModel
import com.andrew.starwars.utils.UiText

sealed class MoviesState {
    object Loading : MoviesState()
    data class Success(val data: List<MovieModel>) : MoviesState()
    data class ShowError(val message: UiText) : MoviesState()


}