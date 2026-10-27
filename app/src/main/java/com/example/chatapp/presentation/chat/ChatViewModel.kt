package com.example.chatapp.presentation.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.usecase.GetOrCreateChat
import com.example.chatapp.domain.usecase.GetUserProfile
import com.example.chatapp.domain.usecase.ObserveMessages
import com.example.chatapp.domain.usecase.SendMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getOrCreateChat: GetOrCreateChat,
    private val sendMessageUseCase : SendMessage,
    private val getUserProfile: GetUserProfile,
    private val observeMessagesUseCase : ObserveMessages
) : ViewModel(){
    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState = _uiState.asStateFlow()

    private val _sendError = MutableStateFlow<String?>(null)
    val sendError = _sendError.asStateFlow()

    private val _isSending = MutableStateFlow(false)
    val isSending = _isSending.asStateFlow()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _messagesError = MutableStateFlow<String?>(null)
    val messagesError = _messagesError.asStateFlow()

    fun openChat(
        currentUserId : String,
        otherUserId : String
    ){
        if(_uiState.value.isLoading) return
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    chat = null,
                    error = null
                )
            }
            try {
                getOrCreateChat(
                    currentUserId = currentUserId,
                    otherUserId = otherUserId
                ).fold(
                    onSuccess = {
                        chat->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                chat = chat,
                                error = null
                            )
                        }
                    },
                    onFailure = {
                        exception ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                chat = null,
                                error = exception.message?: "Unable to open conversation"
                            )
                        }
                    }
                )
            } catch (e : CancellationException){
                throw e
            } catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        chat = null,
                        error = e.message?: "Unable to open conversation"
                    )
                }
            }
        }
    }

    fun clearChatResult(){
        _uiState.update {
            it.copy(
                chat = null,
                error = null
            )
        }
    }

    fun sendMessage(
        chatId : String,
        senderId : String,
        text : String
    ){
        Log.d("ChatDebug", "sendMessage called")
        Log.d("ChatDebug", "chatId = $chatId")
        Log.d("ChatDebug", "senderId = $senderId")
        Log.d("ChatDebug", "text = $text")

        if(_isSending.value || text.isBlank()) return

        viewModelScope.launch {
            _isSending.value = true
            _sendError.value = null

            try {
                sendMessageUseCase(
                    chatId = chatId,
                    senderId = senderId,
                    text = text
                ).onFailure{
                    error->
                    _sendError.value = error.message?: "Failed to send message"
                }
            }catch (e : CancellationException){
                throw e
            }catch (e : Exception){
                Log.e("ChatRepo", "Failed to send message", e)
                _sendError.value = e.message?: "failed to send message"
            }finally {
                _isSending.value = false
            }

        }
    }

    fun loadOtherUserProfile(
        uid : String
    ){
        if(uid.isBlank()) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isProfileLoading = true,
                    otherUser = null,
                    profileError = null
                )
            }
            try {
                val profile = getUserProfile(uid)

                _uiState.update {
                    it.copy(
                        isProfileLoading = false,
                        otherUser = profile,
                        profileError = if(profile == null){
                            "User profile not found"
                        }else{
                            null
                        }
                    )
                }
            }catch (e : CancellationException){
                throw e
            }catch (e : Exception){
                _uiState.update {
                    it.copy(
                        isProfileLoading = false,
                        otherUser = null,
                        profileError = e.message?: "Unable to load user profile"
                    )
                }
            }
        }
    }

    fun observeMessages(chatId : String){
        viewModelScope.launch {
            observeMessagesUseCase(chatId).collect {
                result->
                result.fold(
                    onSuccess = {
                        messages->
                        _messages.value = messages
                        _messagesError.value = null
                    },
                    onFailure = {
                        error->
                        _messagesError.value = error.message ?: "Unable to load message"
                    }
                )
            }
        }
    }

}