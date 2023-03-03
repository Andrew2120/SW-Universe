package com.andrew.starwars.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andrew.starwars.data.mappers.toPerson
import com.andrew.starwars.data.mappers.toPlanet
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.utils.Result

class PlanetsDataSourcePaging(private val dataSource: BaseDataSource) :
    PagingSource<Int, PlanetModel>() {
    var search: String? = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlanetModel> {
        val start = params.key ?: 1
        return dataSource.getPlanets(page = start, search = search).getOrElse {
            return LoadResult.Error(it)
        }.results.let {
            LoadResult.Page(
                data = it.map { it.toPlanet() }, prevKey = when (start) {
                    1 -> null
                    else -> start - 1
                }, nextKey = if (it.isEmpty()) null else start + 1

            )
        }

    }

    override fun getRefreshKey(state: PagingState<Int, PlanetModel>): Int? {
        return null
    }
}