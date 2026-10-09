package com.example.chatapp.presentation.navigation.auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.presentation.screens.LoginScreen
import com.example.chatapp.presentation.screens.RegisterScreen

@Composable
fun AuthNavigation(
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Register
    ){
        composable<Register> {
            RegisterScreen(onLoginButtonClick = {navController.navigate((Login))})
        }

        composable<Login> {
            LoginScreen(onRegisterButtonClick = {navController.navigate(Register)})
        }
    }
}