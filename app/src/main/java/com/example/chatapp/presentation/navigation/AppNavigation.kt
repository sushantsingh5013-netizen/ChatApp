package com.example.chatapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chatapp.data.auth.GoogleAuthClient
import com.example.chatapp.presentation.auth.AuthState
import com.example.chatapp.presentation.auth.AuthViewModel

@Composable
fun AppNavigation(
    viewModel : AuthViewModel = hiltViewModel(),
    googleAuthClient : GoogleAuthClient
){
    val authState by viewModel.authState.collectAsStateWithLifecycle()

    when(authState){
        is AuthState.Error -> {
            AuthNavigation(googleAuthClient)
        }
        AuthState.Idle -> {
            // todo splash screen
        }
        AuthState.Loading -> {
            //todo splash screen
        }
        is AuthState.LoggedIn -> {
            MainNavigation()
        }
        AuthState.LoggedOut -> {
            AuthNavigation(googleAuthClient)
        }
    }
}