package com.andrew.starwars.presentation.ui.home.states


sealed class HomeState {
    data class MoviesLoaded(val loaded: Boolean) : HomeState()
    data class PlanetsLoaded(val loaded: Boolean) : HomeState()
    data class PeopleLoaded(val loaded: Boolean) : HomeState()
    data class SpeciesLoaded(val loaded: Boolean) : HomeState()


}