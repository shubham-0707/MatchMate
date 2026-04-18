package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.MatchEvent
import com.shubham.matchmate.data.model.HeadToHead
import com.shubham.matchmate.data.remote.MockNewFeatureData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface TimelineRepository {
    fun getTimelineEvents(matchId: String): Flow<List<MatchEvent>>
    fun getHeadToHead(team1Id: String, team2Id: String): Flow<HeadToHead>
}

class MockTimelineRepository : TimelineRepository {
    override fun getTimelineEvents(matchId: String): Flow<List<MatchEvent>> = flow {
        emit(MockNewFeatureData.getTimelineEvents(matchId))
    }

    override fun getHeadToHead(team1Id: String, team2Id: String): Flow<HeadToHead> = flow {
        emit(MockNewFeatureData.getHeadToHead(team1Id, team2Id))
    }
}
