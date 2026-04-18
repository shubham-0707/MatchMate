package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.Match
import com.shubham.matchmate.data.repository.MatchRepository
import com.shubham.matchmate.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val matchRepo: MatchRepository,
    private val userRepo: UserRepository
) : ViewModel() {
    private val _liveMatches = MutableStateFlow<List<Match>>(emptyList())
    val liveMatches: StateFlow<List<Match>> = _liveMatches.asStateFlow()

    private val _upcomingMatches = MutableStateFlow<List<Match>>(emptyList())
    val upcomingMatches: StateFlow<List<Match>> = _upcomingMatches.asStateFlow()

    private val _completedMatches = MutableStateFlow<List<Match>>(emptyList())
    val completedMatches: StateFlow<List<Match>> = _completedMatches.asStateFlow()

    val selectedTab = MutableStateFlow(0)

    init {
        loadMatches()
    }

    private fun loadMatches() {
        viewModelScope.launch {
            matchRepo.getLiveMatches().collect { _liveMatches.value = it }
        }
        viewModelScope.launch {
            // Get user's favorite team first, then load upcoming matches
            val user = userRepo.getCurrentUser().first()
            val favoriteTeamId = user?.favoriteTeamId ?: "mi"
            matchRepo.getUpcomingMatches(favoriteTeamId).collect { _upcomingMatches.value = it }
        }
        viewModelScope.launch {
            matchRepo.getCompletedMatches().collect { _completedMatches.value = it }
        }
    }
}
