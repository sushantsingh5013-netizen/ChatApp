package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.User
import com.example.chatapp.domain.repo.AuthRepo
import javax.inject.Inject

class GetCurrentUser @Inject constructor(
    private val repository : AuthRepo
) {

    operator fun invoke() : User?{
        return repository.getCurrentUser()
    }

}