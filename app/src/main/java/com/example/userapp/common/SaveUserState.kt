package com.example.userapp.common

sealed class SaveUserState {
    data object Loading : SaveUserState()
    data class Success(val message: String) : SaveUserState()
    data class Error(val exception: Exception) : SaveUserState()
}