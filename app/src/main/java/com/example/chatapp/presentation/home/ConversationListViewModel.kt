package com.example.chatapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.usecase.GetUserProfile
import com.example.chatapp.domain.usecase.ObserveChats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConversationListViewModel @Inject constructor(
    private val observeChats: ObserveChats,
    private val getUserProfile: GetUserProfile
) : ViewModel(){
    private val _uiState = MutableStateFlow(ChatListUiState())
    val uiState = _uiState.asStateFlow()

    private var observationJob : Job? = null
    private var observedUserId : String? = null

    fun observeConversations(currentUserId : String){
        if(currentUserId.isBlank()) return

        if(
            observedUserId == currentUserId &&
            observationJob?.isActive == true
        ) return

        observationJob?.cancel()
        observedUserId = currentUserId

        _uiState.value = ChatListUiState()

        observationJob = viewModelScope.launch {
            observeChats(currentUserId).collect {
                result ->
                result.fold(
                    onSuccess = {
                        chats->
                        val conversations = coroutineScope {
                            chats.mapNotNull {
                                chat->
                                async {
                                    val otherUserId = chat.participantIds
                                        .firstOrNull{
                                            it != currentUserId
                                        }
                                        ?: return@async null

                                    try {
                                        val profile = getUserProfile(otherUserId)

                                        profile?.let {
                                            ConversationItemUiModel(
                                                chat = chat,
                                                otherUser = it
                                            )
                                        }
                                    }catch (e : CancellationException){
                                        throw e
                                    }catch (_ : Exception){
                                        null
                                    }
                                }
                            }.awaitAll().filterNotNull()
                        }
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                conversations = conversations,
                                error = null
                            )
                        }
                    },
                    onFailure = {
                        exception ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = exception.message?:"Unable to load conversations"
                            )
                        }
                    }
                )
            }
        }
    }

}