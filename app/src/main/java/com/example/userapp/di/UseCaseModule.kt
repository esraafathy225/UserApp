package com.example.userapp.di

import com.example.userapp.domain.repository.UserRepository
import com.example.userapp.domain.usecase.GetUserUseCase
import com.example.userapp.domain.usecase.SaveUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetUserUseCase(
        userRepository: UserRepository
    ): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun provideSaveUserUseCase(
        userRepository: UserRepository
    ): SaveUserUseCase {
        return SaveUserUseCase(userRepository)
    }
}