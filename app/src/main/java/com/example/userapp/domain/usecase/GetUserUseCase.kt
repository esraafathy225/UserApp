package com.example.userapp.domain.usecase

import com.example.userapp.data.model.User
import com.example.userapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor (private val userRepository: UserRepository) {
    operator fun invoke(): Flow<User?> = userRepository.getUser()
}