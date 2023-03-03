package com.andrew.starwars.data.remote

import com.andrew.starwars.data.dto.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SWApis {
    @GET("planets")
    suspend fun getPlanets(
        @Query("format") format: String = "json",
        @Query("page") page: Int,
        @Query("search") search: String? = null
    ): Response<PaginationBaseResponse<PlanetDto>>

    @GET("people")
    suspend fun getPeople(
        @Query("format") format: String = "json",
        @Query("page") page: Int,
        @Query("search") search: String? = null

    ): Response<PaginationBaseResponse<PersonDto>>


    @GET("films")
    suspend fun getMovies(
        @Query("format") format: String = "json",
        @Query("page") page: Int,
        @Query("search") search: String? = null,
    ): Response<PaginationBaseResponse<MovieDto>>

    @GET("species")
    suspend fun getSpecies(
        @Query("format") format: String = "json",
        @Query("page") page: Int,
        @Query("search") search: String? = null,
    ): Response<PaginationBaseResponse<SpeciesDto>>
}