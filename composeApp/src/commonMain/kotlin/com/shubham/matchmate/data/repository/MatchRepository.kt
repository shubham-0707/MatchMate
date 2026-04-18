package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.Match
import com.shubham.matchmate.data.model.MatchStatus
import com.shubham.matchmate.data.remote.MockCricketData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MatchRepository {
    fun getLiveMatches(): Flow<List<Match>>
    fun getUpcomingMatches(favoriteTeamId: String = ""): Flow<List<Match>>
    fun getCompletedMatches(): Flow<List<Match>>
    fun getMatchById(id: String): Flow<Match?>
}

class MockMatchRepository : MatchRepository {
    private var cachedUpcoming: Map<String, List<Match>> = emptyMap()

    override fun getLiveMatches(): Flow<List<Match>> = flow {
        emit(MockCricketData.liveMatches)
    }

    override fun getUpcomingMatches(favoriteTeamId: String): Flow<List<Match>> = flow {
        val teamId = favoriteTeamId.ifEmpty { "mi" }
        // Cache per team so it stays consistent during session
        val matches = cachedUpcoming.getOrElse(teamId) {
            MockCricketData.generateUpcomingMatches(teamId).also {
                cachedUpcoming = cachedUpcoming + (teamId to it)
            }
        }
        emit(matches)
    }

    override fun getCompletedMatches(): Flow<List<Match>> = flow {
        emit(MockCricketData.completedMatches)
    }

    override fun getMatchById(id: String): Flow<Match?> = flow {
        val allMatches = MockCricketData.liveMatches +
            (cachedUpcoming.values.flatten()) +
            MockCricketData.completedMatches
        emit(allMatches.find { it.id == id })
    }
}
