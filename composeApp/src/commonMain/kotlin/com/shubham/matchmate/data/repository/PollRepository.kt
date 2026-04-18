package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.Poll
import com.shubham.matchmate.data.remote.MockCricketData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface PollRepository {
    fun getPollsForMatch(matchId: String): Flow<List<Poll>>
    suspend fun vote(pollId: String, optionId: String)
}

class MockPollRepository : PollRepository {
    private val pollsFlow = MutableStateFlow(MockCricketData.polls)
    private val votedPolls = mutableSetOf<String>()

    override fun getPollsForMatch(matchId: String): Flow<List<Poll>> =
        pollsFlow.map { polls -> polls.filter { it.matchId == matchId } }

    override suspend fun vote(pollId: String, optionId: String) {
        if (pollId in votedPolls) return
        votedPolls.add(pollId)
        pollsFlow.value = pollsFlow.value.map { poll ->
            if (poll.id == pollId) {
                poll.copy(
                    options = poll.options.map { opt ->
                        if (opt.id == optionId) opt.copy(votes = opt.votes + 1) else opt
                    },
                    totalVotes = poll.totalVotes + 1
                )
            } else poll
        }
    }
}
