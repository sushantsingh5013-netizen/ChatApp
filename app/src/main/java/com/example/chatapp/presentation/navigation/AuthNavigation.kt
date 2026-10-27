package com.example.chatapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.data.auth.GoogleAuthClient
import com.example.chatapp.presentation.auth.AuthViewModel
import com.example.chatapp.presentation.auth.LoginScreen
import com.example.chatapp.presentation.auth.RegisterScreen

@Composable
fun AuthNavigation(
    googleAuthClient: GoogleAuthClient
){
    val navController = rememberNavController()
    val viewModel : AuthViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Login
    ){
        composable<Register> {
            RegisterScreen(
                onLoginButtonClick = { navController.navigate(Login) },
                googleAuthClient = googleAuthClient,
                viewModel = viewModel,
                onRegisterSuccess = {
                    navController.navigate(Login)
                },
                onLoggedOut = {
                    navController.navigate(Login)
                }
            )
        }

        composable<Login> {
            LoginScreen(
                onRegisterButtonClick = { navController.navigate(Register) },
                googleAuthClient = googleAuthClient,
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Home){
                        popUpTo<Login>{
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                    // todo navigate to chat screen
                }
            )
        }

    }
}