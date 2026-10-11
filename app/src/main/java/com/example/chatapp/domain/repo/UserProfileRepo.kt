package com.example.chatapp.domain.repo

import com.example.chatapp.domain.model.UserProfile

interface UserProfileRepo {

    suspend fun saveUserProfile(
        user : UserProfile
    )

    suspend fun getUserProfile(
        uid : String
    ): UserProfile?

}