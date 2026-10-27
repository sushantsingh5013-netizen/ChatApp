package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.Chat
import com.example.chatapp.domain.repo.ChatRepo
import javax.inject.Inject

class GetOrCreateChat @Inject constructor(
    private val repository : ChatRepo
) {
    suspend operator fun invoke(
        currentUserId : String,
        otherUserId : String
    ) : Result<Chat>{
        if(currentUserId.isBlank() || otherUserId.isBlank()){
            return Result.failure(
                IllegalArgumentException("User IDs cannot be empty")
            )
        }

        if(currentUserId == otherUserId){
            return Result.failure(
                IllegalArgumentException(
                    "Cannot create a chat with yourself"
                )
            )
        }

        return repository.getOrCreateChat(
            currentUserId,
            otherUserId
        )
    }
}