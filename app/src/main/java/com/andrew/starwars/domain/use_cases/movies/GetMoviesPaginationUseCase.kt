package com.andrew.starwars.domain.use_cases.movies

import androidx.paging.PagingData
import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesPaginationUseCase @Inject constructor(
    private val repo: BaseRepository,
) {
    operator fun invoke(page: Int, search: String?): Flow<PagingData<MovieModel>> {
        return repo.getMoviesWithPagination(page, search = search)
    }
}