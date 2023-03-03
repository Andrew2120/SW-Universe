package com.andrew.starwars.presentation.ui.home.states

import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.utils.UiText

sealed class PlanetsState {
    object Loading : PlanetsState()
    data class Success(val data: List<PlanetModel>) : PlanetsState()
    data class ShowError(val message: UiText) : PlanetsState()



}