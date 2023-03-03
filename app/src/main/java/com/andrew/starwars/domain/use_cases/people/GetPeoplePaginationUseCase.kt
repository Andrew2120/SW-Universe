package com.andrew.starwars.domain.use_cases.people

import androidx.paging.PagingData
import com.andrew.starwars.data.repository.BaseRepository
import com.andrew.starwars.domain.model.PersonModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeoplePaginationUseCase @Inject constructor(
    private val repo: BaseRepository,
) {
    operator fun invoke(page: Int, search: String?): Flow<PagingData<PersonModel>> {
        return repo.getPeopleWithPagination(page, search = search)
    }
}