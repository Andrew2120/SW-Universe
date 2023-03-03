package com.andrew.starwars.presentation.ui.home.states

import com.andrew.starwars.domain.model.SpeciesModel
import com.andrew.starwars.utils.UiText

sealed class SpeciesState {
    object Loading : SpeciesState()
    data class Success(val data: List<SpeciesModel>) : SpeciesState()
    data class ShowError(val message: UiText) : SpeciesState()



}