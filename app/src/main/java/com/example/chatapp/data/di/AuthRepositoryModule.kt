package com.example.chatapp.data.di

import com.example.chatapp.data.repo.AuthRepoImpl
import com.example.chatapp.domain.repo.AuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule{

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl : AuthRepoImpl
    ) : AuthRepo
}