package com.example.chatapp.domain.repo

import com.example.chatapp.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepo {

    suspend fun saveUserProfile(
        user : UserProfile
    )

    suspend fun getUserProfile(
        uid : String
    ): UserProfile?

    fun getUsers() : Flow<List<UserProfile>>

}