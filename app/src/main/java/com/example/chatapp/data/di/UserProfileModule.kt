package com.example.chatapp.data.di

import com.example.chatapp.data.repo.UserProfileRepoImpl
import com.example.chatapp.domain.repo.UserProfileRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserProfileModule {
    @Binds
    @Singleton
    abstract fun bindsUserRepository(
        implementation : UserProfileRepoImpl
    ) : UserProfileRepo

}