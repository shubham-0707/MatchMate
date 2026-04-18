package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.FanProfile
import com.shubham.matchmate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AuthState {
    data object Loading : AuthState
    data class Authenticated(val user: FanProfile) : AuthState
    data object Unauthenticated : AuthState
    data class Error(val message: String) : AuthState
}

class AuthViewModel(private val userRepo: UserRepository) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        checkAuth()
    }

    private fun checkAuth() {
        if (userRepo.isLoggedIn()) {
            viewModelScope.launch {
                userRepo.getCurrentUser().collect { user ->
                    _authState.value = if (user != null) AuthState.Authenticated(user) else AuthState.Unauthenticated
                }
            }
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val success = userRepo.login(email, password)
                if (success) {
                    userRepo.getCurrentUser().collect { user ->
                        if (user != null) _authState.value = AuthState.Authenticated(user)
                    }
                } else {
                    _authState.value = AuthState.Error("Login failed")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun signUp(email: String, password: String, displayName: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val success = userRepo.signUp(email, password, displayName)
                if (success) {
                    userRepo.getCurrentUser().collect { user ->
                        if (user != null) _authState.value = AuthState.Authenticated(user)
                    }
                } else {
                    _authState.value = AuthState.Error("Sign up failed")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun logout() {
        userRepo.logout()
        _authState.value = AuthState.Unauthenticated
    }
}
