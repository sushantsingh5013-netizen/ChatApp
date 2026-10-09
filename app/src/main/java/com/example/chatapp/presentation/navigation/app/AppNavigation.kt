package com.example.chatapp.presentation.navigation.app

import androidx.compose.runtime.Composable
import com.example.chatapp.presentation.navigation.auth.AuthNavigation
import com.example.chatapp.presentation.navigation.auth.AuthState

@Composable
fun AppNavigation(
    authState: AuthState
){

    when(authState){
        is AuthState.Loading ->{
            //Splash Screen
        }
        is AuthState.LoggedIn -> {
            // MainNavigation
        }
        is AuthState.LoggedOut -> {
            AuthNavigation()
        }
    }

}