package com.andrew.starwars.domain.repository

import android.content.Intent
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.andrew.starwars.data.data_sources.*
import com.andrew.starwars.data.mappers.toMovie
import com.andrew.starwars.data.mappers.toPerson
import com.andrew.starwars.data.mappers.toPlanet
import com.andrew.starwars.data.mappers.toSpecies
import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.MovieModel
import com.andrew.starwars.domain.model.PersonModel
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.domain.model.SpeciesModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: BaseDataSource,
    private val planeDataSourcePaging: PlanetsDataSourcePaging,
    private val peopleDataSourcePaging: PeopleDataSourcePaging,
    private val moviesDataSourcePaging: MoviesDataSourcePaging,
    private val speciesDataSourcePaging: SpeciesDataSourcePaging,
    private val firebaseAuth: FirebaseAuth,
) : BaseRepository {

    override fun getPlanetsWithPagination(
        page: Int,
        search: String?
    ): Flow<PagingData<PlanetModel>> {
        planeDataSourcePaging.search = search

        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { planeDataSourcePaging }
        ).flow
    }

    override fun getPeopleWithPagination(
        page: Int,
        search: String?
    ): Flow<PagingData<PersonModel>> {
        peopleDataSourcePaging.search = search
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { peopleDataSourcePaging }
        ).flow
    }

    override fun getMoviesWithPagination(page: Int, search: String?): Flow<PagingData<MovieModel>> {
        moviesDataSourcePaging.search = search
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { moviesDataSourcePaging }
        ).flow
    }

    override fun getSpeciesWithPagination(
        page: Int,
        search: String?
    ): Flow<PagingData<SpeciesModel>> {

        speciesDataSourcePaging.search = search
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { speciesDataSourcePaging }
        ).flow
    }

    override suspend fun getMovies(page: Int): List<MovieModel> {

        return dataSource.getMovies(1, search = null).getOrElse {
            throw Exception(it.localizedMessage)
        }.results.map {
            it.toMovie()
        }
    }

    override suspend fun getPlanets(page: Int): List<PlanetModel> {

        return dataSource.getPlanets(1, search = null).getOrElse {
            throw Exception(it.localizedMessage)
        }.results.map {
            it.toPlanet()
        }
    }

    override suspend fun getPeople(page: Int): List<PersonModel> {
        return dataSource.getPeople(1, search = null).getOrElse {
            throw Exception(it.localizedMessage)
        }.results.map {
            it.toPerson()
        }
    }

    override suspend fun getSpecies(page: Int): List<SpeciesModel> {
        return dataSource.getSpecies(1, search = null).getOrElse {
            throw Exception(it.localizedMessage)
        }.results.map {
            it.toSpecies()
        }
    }

    override suspend fun getUser(): FirebaseUser? = firebaseAuth.currentUser

    override suspend fun logoutUser() {
        firebaseAuth.signOut()
    }



}