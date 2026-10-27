package com.example.chatapp.domain.usecase

import com.example.chatapp.domain.model.Chat
import com.example.chatapp.domain.repo.ChatRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveChats @Inject constructor(
    private val repo : ChatRepo
) {
    operator fun invoke(
        currentUserId : String
    ) : Flow<Result<List<Chat>>> {
        return repo.observeChats(currentUserId)
    }
}