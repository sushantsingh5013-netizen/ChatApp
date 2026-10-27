package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.UserProfile
import com.example.chatapp.domain.repo.UserProfileRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val repository : UserProfileRepo
) {
    operator fun invoke() : Flow<List<UserProfile>> {
        return repository.getUsers()
    }
}