package com.example.chatapp.presentation.navigation.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

sealed interface AuthState {
    object Idle : AuthState
    object Loading : AuthState
    object LoggedOut : AuthState
    data class LoggedIn(val user : FirebaseUser?) : AuthState
}