package com.example.chatapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.chatapp.presentation.chat.toChatMessageUi
import com.example.chatapp.presentation.auth.AuthState
import com.example.chatapp.presentation.auth.AuthViewModel
import com.example.chatapp.presentation.chat.ChatScreen
import com.example.chatapp.presentation.chat.ChatViewModel
import com.example.chatapp.presentation.home.HomeScreen
import com.example.chatapp.presentation.people.PeopleScreen
import com.example.chatapp.presentation.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = Home
    ){
        composable<Home> {
            val viewModel : AuthViewModel = hiltViewModel()
            HomeScreen(
                navController,
                viewModel
            )
        }

        composable <ChatRoute>{
            backStackEntry->

            val authViewModel : AuthViewModel = hiltViewModel()
            val route = backStackEntry.toRoute<ChatRoute>()
            val chatViewModel : ChatViewModel = hiltViewModel()
            val uiState by chatViewModel.uiState.collectAsStateWithLifecycle()

            val authState by authViewModel.authState
                .collectAsStateWithLifecycle()

            val currentUserId =
                (authState as? AuthState.LoggedIn)?.user?.uid

            val messages by chatViewModel.messages.collectAsStateWithLifecycle()

            val messageUiList = messages.map {
                message->
                message.toChatMessageUi(currentUserId)
            }

            LaunchedEffect(route.otherUserId) {
                chatViewModel.loadOtherUserProfile(route.otherUserId)
            }

            LaunchedEffect(route.chatId) {
                chatViewModel.observeMessages(route.chatId)
            }

            val profile = uiState.otherUser

            ChatScreen(
                otherUserName = profile?.name ?: "Loading...",
                otherUserImage = profile?.profileImage,
                isOnline = profile?.isOnline ?: false,
                onBack = {
                    navController.popBackStack()
                },
                onSendMessage = {
                    text->
                    currentUserId?.let {
                        uid->
                        chatViewModel.sendMessage(
                            chatId = route.chatId,
                            senderId = uid,
                            text = text
                        )
                    }
                },
                messages = messageUiList
            )
        }

        composable<Profile> {
            val authViewModel : AuthViewModel = hiltViewModel()
            ProfileScreen(
                onLogOut = {
                    authViewModel.logOut()
                }
            )
        }

        composable <Call>{

        }

        composable <Add>{

        }

        composable <People>{
            PeopleScreen(
                navController
            )
        }
    }
}