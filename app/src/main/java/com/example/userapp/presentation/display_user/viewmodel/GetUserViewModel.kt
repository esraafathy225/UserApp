package com.example.userapp.presentation.display_user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.common.GetUserState
import com.example.userapp.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetUserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
): ViewModel()  {

    // StateFlow for getting user from db
    private val _addUserState = MutableStateFlow<GetUserState>(GetUserState.Loading)
    val addUserState: StateFlow<GetUserState> get() = _addUserState

    fun getUser() {
        viewModelScope.launch {
            _addUserState.value = GetUserState.Loading
            try {
                getUserUseCase().collect { user ->
                    if (user != null) {
                        _addUserState.value = GetUserState.Success(user)
                    } else {
                        _addUserState.value = GetUserState.Error(Exception("No user found"))
                    }
                }
            } catch (e: Exception) {
                _addUserState.value = GetUserState.Error(e)
            }
        }
    }
}