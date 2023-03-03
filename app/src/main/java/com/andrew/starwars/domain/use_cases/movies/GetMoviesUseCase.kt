package com.andrew.starwars.domain.use_cases.movies

import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repo: BaseRepository) {
    operator fun invoke(page: Int): Flow<List<MovieModel>> = flow {
        emit(repo.getMovies(page))
    }
}