package com.example.userapp.data.repository

import com.example.userapp.data.db.UserDao
import com.example.userapp.data.model.User
import com.example.userapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun saveUser(user: User) {
        userDao.saveUser(user)
    }

    override fun getUser(): Flow<User?> {
        return userDao.getUser()
    }
}