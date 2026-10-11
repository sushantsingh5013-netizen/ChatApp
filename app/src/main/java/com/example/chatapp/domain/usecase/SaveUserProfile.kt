package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.UserProfile
import com.example.chatapp.domain.repo.UserProfileRepo
import javax.inject.Inject

class SaveUserProfile @Inject constructor(
    private val repository : UserProfileRepo
) {
    suspend operator fun invoke(
        user : UserProfile
    ) {
        repository.saveUserProfile(user)
    }
}