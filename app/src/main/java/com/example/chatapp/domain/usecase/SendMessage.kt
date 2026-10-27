package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.Message
import com.example.chatapp.domain.model.MessageType
import com.example.chatapp.domain.repo.ChatRepo
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val chatRepo : ChatRepo
) {
    suspend operator fun invoke(
        chatId : String,
        senderId : String,
        text : String
    ) : Result<Unit>{
        if(chatId.isBlank()){
            return Result.failure(
                IllegalArgumentException("Chat ID cannot be empty")
            )
        }
        if (senderId.isBlank()){
            return Result.failure(
                IllegalArgumentException("Sender ID cannot be empty")
            )
        }

        val trimmedText = text.trim()

        if (trimmedText.isEmpty()){
            return Result.failure(
                IllegalArgumentException("Message cannot be empty")
            )
        }

        val message = Message(
            chatId = chatId,
            senderId = senderId,
            text = trimmedText,
            timestamp = System.currentTimeMillis(),
            type = MessageType.TEXT
        )

        return chatRepo.sendMessage(
            chatId = chatId,
            message = message
        )
    }
}