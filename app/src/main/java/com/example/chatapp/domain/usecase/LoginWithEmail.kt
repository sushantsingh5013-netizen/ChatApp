package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.repo.AuthRepo
import javax.inject.Inject

class LoginWithEmail @Inject constructor(
    private val repository : AuthRepo
) {
    suspend operator fun invoke(
        email : String,
        password : String
    ) : Result<User>{
        return repository.signInWithEmail(
            email.trim(),
            password
        )
    }
}