package com.andrew.starwars.domain.use_cases.speices

import androidx.paging.PagingData
import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.PlanetModel
import com.andrew.starwars.domain.model.SpeciesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSpeciesPaginationUseCase @Inject constructor(
    private val repo: BaseRepository,
) {
    operator fun invoke(page: Int, search: String?): Flow<PagingData<SpeciesModel>> {
        return repo.getSpeciesWithPagination(page, search = search)
    }
}