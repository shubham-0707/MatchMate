package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.LeaderboardEntry
import com.shubham.matchmate.data.repository.LeaderboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LeaderboardViewModel(private val leaderboardRepo: LeaderboardRepository) : ViewModel() {
    private val _globalLeaderboard = MutableStateFlow<List<LeaderboardEntry>>(emptyList())
    val globalLeaderboard: StateFlow<List<LeaderboardEntry>> = _globalLeaderboard.asStateFlow()

    private val _matchLeaderboard = MutableStateFlow<List<LeaderboardEntry>>(emptyList())
    val matchLeaderboard: StateFlow<List<LeaderboardEntry>> = _matchLeaderboard.asStateFlow()

    val selectedTab = MutableStateFlow(0) // 0=Global, 1=This Match

    init {
        loadGlobal()
    }

    private fun loadGlobal() {
        viewModelScope.launch {
            leaderboardRepo.getGlobalLeaderboard().collect { _globalLeaderboard.value = it }
        }
    }

    fun loadMatchLeaderboard(matchId: String) {
        viewModelScope.launch {
            leaderboardRepo.getMatchLeaderboard(matchId).collect { _matchLeaderboard.value = it }
        }
    }
}
