package com.andrew.starwars.domain.use_cases.planets

import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.PlanetModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(private val repo: BaseRepository) {
    operator fun invoke(page: Int): Flow<List<PlanetModel>> = flow {
        emit(repo.getPlanets(page))
    }
}