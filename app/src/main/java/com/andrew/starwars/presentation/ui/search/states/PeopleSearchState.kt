package com.andrew.starwars.presentation.ui.search.states

import androidx.paging.PagingData
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.utils.UiText

sealed class PeopleSearchState {
    object Loading : PeopleSearchState()
    data class Success(val data: PagingData<PersonModel>) : PeopleSearchState()
    data class ShowError(val message: UiText) : PeopleSearchState()



}