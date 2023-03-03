package com.andrew.starwars.data.data_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andrew.starwars.data.mappers.toPerson
import com.andrew.starwars.data.mappers.toPlanet
import com.andrew.starwars.data.mappers.toSpecies
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.domain.model.SpeciesModel
import com.andrew.starwars.utils.Result

class SpeciesDataSourcePaging(private val dataSource: BaseDataSource) :
    PagingSource<Int, SpeciesModel>() {
    var search: String? = ""

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SpeciesModel> {
        val start = params.key ?: 1
        return dataSource.getSpecies(page = start, search = search).getOrElse {
            return LoadResult.Error(it)
        }.results.let {
            LoadResult.Page(
                data = it.map { it.toSpecies() }, prevKey = when (start) {
                    1 -> null
                    else -> start - 1
                }, nextKey = if (it.isEmpty()) null else start + 1

            )
        }

    }

    override fun getRefreshKey(state: PagingState<Int, SpeciesModel>): Int? {
        return null
    }
}