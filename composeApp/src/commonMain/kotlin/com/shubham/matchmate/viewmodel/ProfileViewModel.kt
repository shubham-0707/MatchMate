package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.FanProfile
import com.shubham.matchmate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepo: UserRepository) : ViewModel() {
    private val _profile = MutableStateFlow<FanProfile?>(null)
    val profile: StateFlow<FanProfile?> = _profile.asStateFlow()

    init {
        viewModelScope.launch {
            userRepo.getCurrentUser().collect { _profile.value = it }
        }
    }

    fun updateDisplayName(name: String) {
        viewModelScope.launch { userRepo.updateDisplayName(name) }
    }
}
