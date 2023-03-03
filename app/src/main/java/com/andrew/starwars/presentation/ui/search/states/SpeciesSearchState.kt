package com.andrew.starwars.presentation.ui.search.states

import androidx.paging.PagingData
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.domain.model.SpeciesModel
import com.andrew.starwars.utils.UiText

sealed class SpeciesSearchState {
    object Loading : SpeciesSearchState()
    data class Success(val data: PagingData<SpeciesModel>) : SpeciesSearchState()
    data class ShowError(val message: UiText) : SpeciesSearchState()



}