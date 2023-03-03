package com.andrew.starwars.presentation.ui.search.states

import androidx.paging.PagingData
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.utils.UiText

sealed class PlanetsSearchState {
    object Loading : PlanetsSearchState()
    data class Success(val data: PagingData<PlanetModel>) : PlanetsSearchState()
    data class ShowError(val message: UiText) : PlanetsSearchState()



}