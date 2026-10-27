package com.example.chatapp.presentation.people

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.chatapp.presentation.chat.ChatViewModel
import com.example.chatapp.presentation.components.ChatBottomBar
import com.example.chatapp.presentation.navigation.ChatRoute
import com.example.chatapp.presentation.people.components.PeopleSearchBar
import com.example.chatapp.presentation.people.components.UserListComponent
import com.example.chatapp.presentation.people.components.UserTopBar

@Composable
fun PeopleScreen(
    navController : NavHostController,
){
    val viewModel: PeopleViewModel = hiltViewModel()
    val chatViewModel : ChatViewModel = hiltViewModel()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val chatState by chatViewModel.uiState.collectAsStateWithLifecycle()

    val currentUserId = viewModel.currentUserid

    val snackbarHostState = remember { SnackbarHostState() }

    val filteredUsers = uiState.users.filter {
        user->
        val query = uiState.searchQuery.trim()

        user.name.contains(
            query,
            ignoreCase = true
        ) || user.email.contains(
            query,
            ignoreCase = true
        )
    }

    Scaffold(
        topBar = {
            UserTopBar()
        },

        bottomBar = {
            ChatBottomBar(
                navController
            )
        },
        modifier = Modifier.padding(horizontal = 20.dp),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            PeopleSearchBar(
                query = uiState.searchQuery,
                onQueryChange = viewModel::onSearchQueryChange
            )
            Spacer(modifier = Modifier.height(12.dp))

            LaunchedEffect(chatState.chat) {
                val chat = chatState.chat ?: return@LaunchedEffect
                val currentUid = viewModel.currentUserid ?: return@LaunchedEffect

                val otherUserId = chat.participantIds.firstOrNull{
                    it != currentUid
                }?: return@LaunchedEffect

                navController.navigate(
                    ChatRoute(
                        chatId = chat.id,
                        otherUserId = otherUserId
                    )
                )
                chatViewModel.clearChatResult()

            }

            LaunchedEffect(chatState.error) {
                val error = chatState.error ?: return@LaunchedEffect

                snackbarHostState.showSnackbar(error)
                chatViewModel.clearChatResult()
            }

            if(chatState.isLoading){
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
            }

            when{
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
                            text = uiState.error?: "Something went wrong"
                        )
                    }
                }

                uiState.users.isEmpty()->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "No users found"
                        )
                    }
                }

                else ->{
                    LazyColumn{
                        items(
                            items = filteredUsers,
                            key = {user -> user.uid}
                        ){
                            user->
                            UserListComponent(
                                user = user,
                                onClick = {
                                val currentUid = viewModel.currentUserid

                                if(currentUid != null){
                                    chatViewModel.openChat(
                                        currentUserId = currentUid,
                                        otherUserId = user.uid
                                    )
                                }
                            })
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                }
            }
        }
    }
}
