package com.andrew.starwars.domain.use_cases.speices

import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.SpeciesModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSpeciesUseCase @Inject constructor(private val repo: BaseRepository) {
    operator fun invoke(page: Int, search: String?): Flow<List<SpeciesModel>> = flow {
        emit(repo.getSpecies(page))
    }
}