package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.LeaderboardEntry
import com.shubham.matchmate.data.remote.MockNewFeatureData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface LeaderboardRepository {
    fun getGlobalLeaderboard(): Flow<List<LeaderboardEntry>>
    fun getMatchLeaderboard(matchId: String): Flow<List<LeaderboardEntry>>
}

class MockLeaderboardRepository : LeaderboardRepository {
    override fun getGlobalLeaderboard(): Flow<List<LeaderboardEntry>> = flow {
        emit(MockNewFeatureData.globalLeaderboard)
    }

    override fun getMatchLeaderboard(matchId: String): Flow<List<LeaderboardEntry>> = flow {
        emit(MockNewFeatureData.getMatchLeaderboard(matchId))
    }
}
