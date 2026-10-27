package com.example.chatapp.data.di

import com.example.chatapp.data.repo.ChatRepoImpl
import com.example.chatapp.domain.repo.ChatRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ChatModule {
    @Binds
    @Singleton
    abstract fun bindChatRepository(
        implementation : ChatRepoImpl
    ) : ChatRepo
}