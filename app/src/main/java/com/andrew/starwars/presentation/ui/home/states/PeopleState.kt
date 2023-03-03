package com.andrew.starwars.presentation.ui.home.states

import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.utils.UiText

sealed class PeopleState {
    object Loading : PeopleState()
    data class Success(val data: List<PersonModel>) : PeopleState()
    data class ShowError(val message: UiText) : PeopleState()



}