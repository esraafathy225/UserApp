package com.example.userapp.presentation.save_user.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.common.SaveUserState
import com.example.userapp.data.model.User
import com.example.userapp.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveUserViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
): ViewModel() {
    // StateFlow for saving user in db
    private val _saveUserState = MutableStateFlow<SaveUserState>(SaveUserState.Loading)
    val saveUserState: StateFlow<SaveUserState> get() = _saveUserState

    // StateFlow for holding user inputs
    private val _name = MutableStateFlow("")
    private val _age = MutableStateFlow("")
    private val _jobTitle = MutableStateFlow("")
    private val _gender = MutableStateFlow("")

    // State flags to track field interactions
    private var isNameTouched = false
    private var isAgeTouched = false
    private var isJobTitleTouched = false
    private var isGenderTouched = false

    // StateFlow for validation status
    private val _inputValid = MutableStateFlow(false)
    val inputValid: StateFlow<Boolean> = _inputValid.asStateFlow()

    // Error messages in a single state
    private val _errorMessages = MutableStateFlow(mapOf<String, String?>())
    val errorMessages: StateFlow<Map<String, String?>> = _errorMessages.asStateFlow()

    // Update input fields
    fun updateInput(field: String, value: String) {
        when (field) {
            "name" -> {
                isNameTouched = true
                _name.value = value
            }

            "age" -> {
                isAgeTouched = true
                _age.value = value
            }

            "jobTitle" -> {
                isJobTitleTouched = true
                _jobTitle.value = value
            }

            "gender" -> {
                isGenderTouched = true
                _gender.value = value
            }
        }
        validateInputs()
    }

    // Validate inputs and set errors
    private fun validateInputs() {
        val errors = mutableMapOf<String, String?>()
        if (isNameTouched && _name.value.isBlank()) errors["name"] = "Name cannot be empty"
        if (isAgeTouched && _age.value.isBlank()) errors["age"] = "Invalid age"
        if (isJobTitleTouched && _jobTitle.value.isBlank()) errors["jobTitle"] = "Job Title cannot be empty"
        if (isGenderTouched && _gender.value.isBlank()) errors["gender"] = "Gender cannot be empty"
        _errorMessages.value = errors
        _inputValid.value = errors.all { it.value == null } // If no errors, inputs are valid
    }

   fun saveUser(user: User) {
        viewModelScope.launch {
            _saveUserState.value = SaveUserState.Loading
            try {
                 saveUserUseCase(user)
                _saveUserState.value = SaveUserState.Success("User saved successfully")
            } catch (e: Exception) {
                _saveUserState.value = SaveUserState.Error(e)
            }
        }
    }

}