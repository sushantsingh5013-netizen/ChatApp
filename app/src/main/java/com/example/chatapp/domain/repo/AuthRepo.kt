package com.example.chatapp.domain.repo

import com.example.chatapp.domain.model.User

interface AuthRepo {

    suspend fun signInWithGoogle(
        idToken : String
    ): Result<User>

    fun getCurrentUser() : User?

    suspend fun signOut()

    suspend fun signInWithEmail(
        email : String,
        password : String
    ) : Result<User>

    suspend fun registerWithEmail(
        name : String,
        email: String,
        password: String
    ) : Result<User>

}