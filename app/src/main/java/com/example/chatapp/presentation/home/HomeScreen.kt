package com.example.chatapp.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.chatapp.presentation.auth.AuthState
import com.example.chatapp.presentation.auth.AuthViewModel
import com.example.chatapp.presentation.components.ChatAppTopBar
import com.example.chatapp.presentation.components.ChatBottomBar
import com.example.chatapp.presentation.navigation.ChatRoute
import com.example.chatapp.presentation.navigation.People

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController : NavHostController,
    viewModel: AuthViewModel,
    conversationViewModel : ConversationListViewModel = hiltViewModel()
){
    val authState by viewModel.authState.collectAsStateWithLifecycle()
    val uiState by conversationViewModel.uiState.collectAsStateWithLifecycle()

    val currentUserId = (authState as? AuthState.LoggedIn)?.user?.uid

    LaunchedEffect(currentUserId) {
        if(currentUserId != null){
            conversationViewModel.observeConversations(currentUserId)
        }
    }

    Scaffold(
        topBar = { ChatAppTopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(People)},
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "add icon"
                )
            }
        },
        bottomBar = { ChatBottomBar( navController ) },
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) { innerPadding->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            HomeSearchBar()
            Text("Welcome", fontSize = 50.sp)

            when{
                currentUserId == null ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
                uiState.isLoading->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
                uiState.error != null ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = uiState.error?: "unable to load conversations"
                        )
                    }
                }

                uiState.conversations.isEmpty()->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "No conversations yet. Start chatting!"
                        )
                    }
                }
                else->{
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = uiState.conversations,
                            key = { it.chat.id }
                        ){
                            conversation->
                            ConversationItem(
                                user = conversation.otherUser,
                                lastMessage = conversation.chat.lastMessage,
                                lastMessageAt = conversation.chat.lastMessageAt,
                                onClick = {
                                    navController.navigate(
                                        ChatRoute(
                                            chatId = conversation.chat.id,
                                            otherUserId = conversation.otherUser.uid
                                        )
                                    )
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }

        }
    }
}
