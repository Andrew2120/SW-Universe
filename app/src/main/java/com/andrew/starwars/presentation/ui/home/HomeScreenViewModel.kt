package com.andrew.starwars.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.starwars.R
import com.andrew.starwars.domain.use_cases.movies.GetMoviesUseCase
import com.andrew.starwars.domain.use_cases.people.GetPeopleUseCase
import com.andrew.starwars.domain.use_cases.planets.GetPlanetsUseCase
import com.andrew.starwars.domain.use_cases.speices.GetSpeciesUseCase
import com.andrew.starwars.presentation.ui.home.states.*
import com.andrew.starwars.utils.DispatcherProvider
import com.andrew.starwars.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesUseCase: GetMoviesUseCase,
    private val planetsUseCase: GetPlanetsUseCase,
    private val speciesUseCase: GetSpeciesUseCase,
    private val peopleUseCase: GetPeopleUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {


    private val _planetFlow = MutableStateFlow<PlanetsState>(PlanetsState.Loading)
    val planetFlow = _planetFlow.asStateFlow()

    private val _peopleFlow = MutableStateFlow<PeopleState>(PeopleState.Loading)
    val peopleFlow = _peopleFlow.asStateFlow()

    private val _moviesFlow = MutableStateFlow<MoviesState>(MoviesState.Loading)
    val moviesFlow = _moviesFlow.asStateFlow()

    private val _speciesFlow = MutableStateFlow<SpeciesState>(SpeciesState.Loading)
    val speciesFlow = _speciesFlow.asStateFlow()




    init {
        getPlanets()
        getPeople()
        getMovies()
        getSpecies()
    }

    private fun getPlanets() {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val planets = planetsUseCase(1)
                _planetFlow.emit(PlanetsState.Success(planets.first()))
            } catch (e: Exception) {
                if (e.message != null) {
                    _planetFlow.emit(PlanetsState.ShowError(UiText.DynamicString(e.message!!)))
                } else {
                    _planetFlow.emit(PlanetsState.ShowError(UiText.StringResource(R.string.somethingWentWrong)))
                }


            }
        }
    }

    private fun getPeople() {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val people = peopleUseCase(1)
                _peopleFlow.emit(PeopleState.Success(people.first()))

            } catch (e: Exception) {
                if (e.message != null) {
                    _peopleFlow.emit(PeopleState.ShowError(UiText.DynamicString(e.message!!)))
                } else {
                    _peopleFlow.emit(PeopleState.ShowError(UiText.StringResource(R.string.somethingWentWrong)))
                }


            }
        }
    }

    private fun getMovies() {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val movies = moviesUseCase(1)
                _moviesFlow.emit(MoviesState.Success(movies.first()))

            } catch (e: Exception) {
                if (e.message != null) {
                    _moviesFlow.emit(MoviesState.ShowError(UiText.DynamicString(e.message!!)))
                } else {
                    _moviesFlow.emit(MoviesState.ShowError(UiText.StringResource(R.string.somethingWentWrong)))
                }


            }
        }
    }

    private fun getSpecies() {
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val species = speciesUseCase(1, search = null)
                _speciesFlow.emit(SpeciesState.Success(species.first()))

            } catch (e: Exception) {
                if (e.message != null) {
                    _speciesFlow.emit(SpeciesState.ShowError(UiText.DynamicString(e.message!!)))
                } else {
                    _speciesFlow.emit(SpeciesState.ShowError(UiText.StringResource(R.string.somethingWentWrong)))
                }


            }
        }
    }
}