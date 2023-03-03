package com.andrew.starwars.domain.use_cases.planets

import androidx.paging.PagingData
import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.PlanetModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlanetsPaginationUseCase @Inject constructor(
    private val repo: BaseRepository,
) {
    operator fun invoke(page: Int, search: String?): Flow<PagingData<PlanetModel>> {
        return repo.getPlanetsWithPagination(page, search = search)
    }
}