package com.example.chatapp.presentation.navigation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.data.auth.GoogleAuthClient
import com.example.chatapp.presentation.navigation.app.Chat
import com.example.chatapp.presentation.screens.LoginScreen
import com.example.chatapp.presentation.screens.RegisterScreen

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
                    navController.navigate(Chat){
                        popUpTo<Chat>{
                            inclusive = true
                        }

                    }
                    // todo navigate to chat screen
                }
            )
        }

        composable <Chat>{
            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text("Welcome", fontSize = 50.sp)
                Button(
                    onClick = {
                        viewModel.logOut()
                    }
                ) {
                    Text("LogOut")
                }
            }
        }
    }
}