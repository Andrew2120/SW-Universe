package com.andrew.starwars.data.repository

import androidx.paging.PagingData
import com.andrew.starwars.domain.model.MovieModel
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.domain.model.SpeciesModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface BaseRepository {

    fun getPlanetsWithPagination(page: Int, search: String?): Flow<PagingData<PlanetModel>>
    fun getPeopleWithPagination(page: Int, search: String?): Flow<PagingData<PersonModel>>
    fun getMoviesWithPagination(page: Int, search: String?): Flow<PagingData<MovieModel>>
    fun getSpeciesWithPagination(page: Int, search: String?): Flow<PagingData<SpeciesModel>>

    suspend fun getMovies(page: Int): List<MovieModel>
    suspend fun getPlanets(page: Int): List<PlanetModel>
    suspend fun getPeople(page: Int): List<PersonModel>
    suspend fun getSpecies(page: Int): List<SpeciesModel>

    suspend fun getUser(): FirebaseUser?
    suspend fun logoutUser()

}