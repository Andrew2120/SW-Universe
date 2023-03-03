package com.andrew.starwars.domain.use_cases.people

import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.PersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(private val repo: BaseRepository) {
    operator fun invoke(page: Int): Flow<List<PersonModel>> = flow {
        emit(repo.getPeople(page))
    }
}