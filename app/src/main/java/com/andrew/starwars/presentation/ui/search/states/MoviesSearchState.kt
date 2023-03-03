package com.andrew.starwars.presentation.ui.search.states

import androidx.paging.PagingData
import com.andrew.starwars.domain.model.MovieModel
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.utils.UiText

sealed class MoviesSearchState {
    object Loading : MoviesSearchState()
    data class Success(val data: PagingData<MovieModel>) : MoviesSearchState()
    data class ShowError(val message: UiText) : MoviesSearchState()



}