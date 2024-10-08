package com.example.userapp.domain.repository
import com.example.userapp.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(user: User)
    fun getUser(): Flow<User?>
}