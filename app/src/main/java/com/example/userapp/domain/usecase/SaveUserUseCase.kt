package com.example.userapp.domain.usecase

import com.example.userapp.data.model.User
import com.example.userapp.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) {
        userRepository.saveUser(user)
    }
}