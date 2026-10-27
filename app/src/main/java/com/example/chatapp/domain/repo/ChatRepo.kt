package com.example.chatapp.domain.repo

import com.example.chatapp.domain.model.Chat
import com.example.chatapp.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepo {

    suspend fun getOrCreateChat(
        currentUserId : String,
        otherUserId : String
    ) : Result<Chat>

    suspend fun sendMessage(
        chatId : String,
        message : Message
    ) : Result<Unit>

    fun observeChats(
        currentUserId : String
    ) : Flow<Result<List<Chat>>>

    fun observeMessages(
        chatId : String
    ) : Flow<Result<List<Message>>>

}