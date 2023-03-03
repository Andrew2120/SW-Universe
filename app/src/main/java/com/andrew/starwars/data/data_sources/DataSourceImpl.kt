package com.andrew.starwars.data.data_sources

import com.andrew.starwars.data.dto.*
import com.andrew.starwars.data.remote.SWApis
import com.andrew.starwars.data.remote.error.ErrorModel
import com.andrew.starwars.utils.ConnectivityService
import com.andrew.starwars.data.remote.error.Errors.Companion.NOT_FOUND
import com.andrew.starwars.data.remote.error.Errors.Companion.NO_INTERNET_CONNECTION
import com.google.gson.Gson
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val api: SWApis,
    private val connectivityService: ConnectivityService,
) : BaseDataSource {

    override suspend fun getPlanets(
        page: Int,
        search: String?
    ): Result<PaginationBaseResponse<PlanetDto>> {
        return if (connectivityService.isNetworkAvailable()) {
            val results = api.getPlanets(page = page, search = search)
            return if (results.isSuccessful) {
                Result.success(results.body()!!)
            } else if (results.code() == 404) {
                Result.failure(Exception(NOT_FOUND))
            } else {
                val errorResponse: ErrorModel = Gson().fromJson(
                    results.errorBody()?.string(),
                    ErrorModel::class.java
                )
                Result.failure(Exception(errorResponse.detail))
            }
        } else {
            Result.failure(Exception(NO_INTERNET_CONNECTION))
        }
    }

    override suspend fun getPeople(
        page: Int,
        search: String?
    ): Result<PaginationBaseResponse<PersonDto>> {
        return if (connectivityService.isNetworkAvailable()) {
            val results = api.getPeople(page = page, search = search)
            return if (results.isSuccessful) {
                Result.success(results.body()!!)
            } else if (results.code() == 404) {
                Result.failure(Exception(NOT_FOUND))
            } else {
                val errorResponse: ErrorModel = Gson().fromJson(
                    results.errorBody()?.string(),
                    ErrorModel::class.java
                )
                Result.failure(Exception(errorResponse.detail))
            }
        } else {
            Result.failure(Exception(NO_INTERNET_CONNECTION))
        }
    }

    override suspend fun getMovies(
        page: Int,
        search: String?
    ): Result<PaginationBaseResponse<MovieDto>> {
        return if (connectivityService.isNetworkAvailable()) {
            val results = api.getMovies(page = page, search = search)
            return if (results.isSuccessful) {
                Result.success(results.body()!!)
            } else if (results.code() == 404) {
                Result.failure(Exception(NOT_FOUND))
            } else {
                val errorResponse: ErrorModel = Gson().fromJson(
                    results.errorBody()?.string(),
                    ErrorModel::class.java
                )
                Result.failure(Exception(errorResponse.detail))
            }
        } else {
            Result.failure(Exception(NO_INTERNET_CONNECTION))
        }
    }

    override suspend fun getSpecies(
        page: Int, search: String?
    ): Result<PaginationBaseResponse<SpeciesDto>> {
        return if (connectivityService.isNetworkAvailable()) {
            val results = api.getSpecies(page = page, search = search)
            return if (results.isSuccessful) {
                Result.success(results.body()!!)
            } else if (results.code() == 404) {
                Result.failure(Exception(NOT_FOUND))
            } else {
                val errorResponse: ErrorModel = Gson().fromJson(
                    results.errorBody()?.string(),
                    ErrorModel::class.java
                )
                Result.failure(Exception(errorResponse.detail))
            }
        } else {
            Result.failure(Exception(NO_INTERNET_CONNECTION))
        }
    }


}