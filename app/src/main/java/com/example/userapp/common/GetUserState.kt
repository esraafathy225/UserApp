package com.example.userapp.common

import com.example.userapp.data.model.User

sealed class GetUserState {
    data object Loading : GetUserState()
    data class Success(val user: User) : GetUserState()
    data class Error(val exception: Exception) : GetUserState()
}