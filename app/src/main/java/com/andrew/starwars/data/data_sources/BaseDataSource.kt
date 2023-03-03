package com.andrew.starwars.data.data_sources

import com.andrew.starwars.data.dto.*

interface BaseDataSource {
    suspend fun getPlanets(page: Int, search: String?): Result<PaginationBaseResponse<PlanetDto>>
    suspend fun getPeople(page: Int, search: String?): Result<PaginationBaseResponse<PersonDto>>
    suspend fun getMovies(page: Int, search: String?): Result<PaginationBaseResponse<MovieDto>>
    suspend fun getSpecies(page: Int, search: String?): Result<PaginationBaseResponse<SpeciesDto>>

}