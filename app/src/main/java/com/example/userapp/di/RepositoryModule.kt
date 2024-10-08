package com.example.userapp.di

import com.example.userapp.data.db.UserDao
import com.example.userapp.data.repository.UserRepositoryImpl
import com.example.userapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(userDao)
    }
}