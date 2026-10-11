package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.repo.AuthRepo
import javax.inject.Inject

class RegisterWithEmail @Inject constructor(
    private val repository : AuthRepo
) {
    suspend operator fun invoke(
        name : String,
        email : String,
        password : String
    ) : Result<User>{
        return repository.registerWithEmail(
            name = name.trim(),
            email = email.trim(),
            password = password
        )
    }
}