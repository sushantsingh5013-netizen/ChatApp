package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.UserProfile
import com.example.chatapp.domain.repo.UserProfileRepo
import javax.inject.Inject

class GetUserProfile @Inject constructor(
    private val repo : UserProfileRepo
) {
    suspend operator fun invoke(
        uid : String
    ) : UserProfile?{
        return repo.getUserProfile(uid)
    }

}