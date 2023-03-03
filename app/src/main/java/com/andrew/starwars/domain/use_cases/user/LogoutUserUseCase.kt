package com.andrew.starwars.domain.use_cases.user

import com.andrew.starwars.data.repository.BaseRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val repo: BaseRepository) {
    operator fun invoke() = flow {
        emit(repo.logoutUser())
    }
}