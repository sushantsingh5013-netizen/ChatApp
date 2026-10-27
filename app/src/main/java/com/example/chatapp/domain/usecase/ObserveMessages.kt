package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.repo.ChatRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMessages @Inject constructor(
    private val chatRepo : ChatRepo
){
    operator fun invoke(
        chatId : String
    ): Flow<Result<List<Message>>>{
        return chatRepo.observeMessages(chatId)
    }
}