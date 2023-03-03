package com.andrew.starwars.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.andrew.starwars.data.data_sources.PlanetsDataSourcePaging
import com.andrew.starwars.domain.use_cases.movies.GetMoviesPaginationUseCase
import com.andrew.starwars.domain.use_cases.people.GetPeoplePaginationUseCase
import com.andrew.starwars.domain.use_cases.planets.GetPlanetsPaginationUseCase
import com.andrew.starwars.domain.use_cases.speices.GetSpeciesPaginationUseCase
import com.andrew.starwars.presentation.ui.home.states.PeopleState
import com.andrew.starwars.presentation.ui.home.states.PlanetsState
import com.andrew.starwars.presentation.ui.search.states.MoviesSearchState
import com.andrew.starwars.presentation.ui.search.states.PeopleSearchState
import com.andrew.starwars.presentation.ui.search.states.PlanetsSearchState
import com.andrew.starwars.presentation.ui.search.states.SpeciesSearchState
import com.andrew.starwars.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val peoplePaginationUseCase: GetPeoplePaginationUseCase,
    private val moviesPaginationUseCase: GetMoviesPaginationUseCase,
    private val planetsPaginationUseCase: GetPlanetsPaginationUseCase,
    private val speciesPaginationUseCase: GetSpeciesPaginationUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {


    private val _peopleFlow = MutableStateFlow<PeopleSearchState>(PeopleSearchState.Loading)
    val peopleFlow = _peopleFlow.asStateFlow()

    private val _moviesFlow = MutableStateFlow<MoviesSearchState>(MoviesSearchState.Loading)
    val moviesFlow = _moviesFlow.asStateFlow()

    private val _planetsFlow = MutableStateFlow<PlanetsSearchState>(PlanetsSearchState.Loading)
    val planetsFlow = _planetsFlow.asStateFlow()

    private val _speciesFlow = MutableStateFlow<SpeciesSearchState>(SpeciesSearchState.Loading)
    val speciesFlow = _speciesFlow.asStateFlow()

    val searchType = MutableLiveData<Int>().apply {
        value = 0
    }

    val searchQuery = MutableLiveData<String>().apply {
        value = ""
    }


    fun searchPeople() {
        viewModelScope.launch(dispatcherProvider.io) {
            peoplePaginationUseCase(1, searchQuery.value).cachedIn(viewModelScope)
                .onStart {
                    _peopleFlow.emit(PeopleSearchState.Loading)
                }.map {
                    withContext(dispatcherProvider.main) {
                        _peopleFlow.emit(
                            PeopleSearchState.Success(
                                it
                            )
                        )
                    }
                }.collect()
        }
    }
    fun searchPlanets() {
        viewModelScope.launch(dispatcherProvider.io) {
            planetsPaginationUseCase(1, searchQuery.value).cachedIn(viewModelScope)
                .onStart {
                    _planetsFlow.emit(PlanetsSearchState.Loading)
                }.map {
                    withContext(dispatcherProvider.main) {
                        _planetsFlow.emit(
                            PlanetsSearchState.Success(
                                it
                            )
                        )
                    }
                }.collect()
        }
    }
    fun searchMovies() {
        viewModelScope.launch(dispatcherProvider.io) {
            moviesPaginationUseCase(1, searchQuery.value).cachedIn(viewModelScope)
                .onStart {
                    _moviesFlow.emit(MoviesSearchState.Loading)
                }.map {
                    withContext(dispatcherProvider.main) {
                        _moviesFlow.emit(
                            MoviesSearchState.Success(
                                it
                            )
                        )
                    }
                }.collect()
        }
    }
    fun searchSpecies() {
        viewModelScope.launch(dispatcherProvider.io) {
            speciesPaginationUseCase(1, searchQuery.value).cachedIn(viewModelScope)
                .onStart {
                    _speciesFlow.emit(SpeciesSearchState.Loading)
                }.map {
                    withContext(dispatcherProvider.main) {
                        _speciesFlow.emit(
                            SpeciesSearchState.Success(
                                it
                            )
                        )
                    }
                }.collect()
        }
    }

}