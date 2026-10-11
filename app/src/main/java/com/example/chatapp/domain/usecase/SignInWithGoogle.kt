package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.repo.AuthRepo
import javax.inject.Inject

class SignInWithGoogle @Inject constructor(
    private val repository : AuthRepo
) {
    suspend operator fun invoke(
        idToken : String
    ) = repository.signInWithGoogle(idToken)
}