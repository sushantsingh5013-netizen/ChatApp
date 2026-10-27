package com.example.chatapp.presentation.auth

import com.example.chatapp.domain.model.User

sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    object LoggedOut : AuthState
    data class LoggedIn(val user : User) : AuthState
    data class Error(val message : String) : AuthState
}