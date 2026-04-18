package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.Badge
import com.shubham.matchmate.data.model.FanProfile
import com.shubham.matchmate.data.model.Team
import com.shubham.matchmate.data.remote.MockCricketData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface UserRepository {
    fun getCurrentUser(): Flow<FanProfile?>
    fun getTeams(): List<Team>
    suspend fun login(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String, displayName: String): Boolean
    suspend fun selectFavoriteTeam(teamId: String)
    suspend fun updateDisplayName(name: String)
    fun isLoggedIn(): Boolean
    fun logout()
}

class MockUserRepository : UserRepository {
    private val userFlow = MutableStateFlow<FanProfile?>(null)
    private var loggedIn = false

    override fun getCurrentUser(): Flow<FanProfile?> = userFlow

    override fun getTeams(): List<Team> = MockCricketData.teams

    override suspend fun login(email: String, password: String): Boolean {
        loggedIn = true
        userFlow.value = MockCricketData.currentUser
        return true
    }

    override suspend fun signUp(email: String, password: String, displayName: String): Boolean {
        loggedIn = true
        userFlow.value = MockCricketData.currentUser.copy(displayName = displayName)
        return true
    }

    override suspend fun selectFavoriteTeam(teamId: String) {
        userFlow.value = userFlow.value?.copy(favoriteTeamId = teamId)
    }

    override suspend fun updateDisplayName(name: String) {
        userFlow.value = userFlow.value?.copy(displayName = name)
    }

    override fun isLoggedIn(): Boolean = loggedIn

    override fun logout() {
        loggedIn = false
        userFlow.value = null
    }
}
