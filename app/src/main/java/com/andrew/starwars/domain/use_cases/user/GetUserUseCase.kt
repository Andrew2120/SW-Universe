package com.andrew.starwars.domain.use_cases.user

import com.andrew.starwars.data.repository.BaseRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: BaseRepository) {
    operator fun invoke(): Flow<FirebaseUser?> = flow {
        emit(repo.getUser())
    }
}