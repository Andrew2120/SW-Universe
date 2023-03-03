package com.andrew.starwars.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andrew.starwars.data.mappers.toMovie
import com.andrew.starwars.data.mappers.toPerson
import com.andrew.starwars.data.mappers.toPlanet
import com.andrew.starwars.domain.model.MovieModel
import com.andrew.starwars.utils.Result

class MoviesDataSourcePaging(private val dataSource: BaseDataSource) :
    PagingSource<Int, MovieModel>() {
    var search: String? = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val start = params.key ?: 1
        return dataSource.getMovies(page = start, search = search).getOrElse {
            return LoadResult.Error(it)
        }.results.let {
            LoadResult.Page(
                data = it.map { it.toMovie() }, prevKey = when (start) {
                    1 -> null
                    else -> start - 1
                }, nextKey = if (it.isEmpty()) null else start + 1

            )
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return null
    }
}