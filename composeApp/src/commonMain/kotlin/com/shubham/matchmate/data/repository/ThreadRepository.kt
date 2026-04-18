package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.Comment
import com.shubham.matchmate.data.model.PostMatchThread
import com.shubham.matchmate.data.remote.MockCricketData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface ThreadRepository {
    fun getThreadsForMatch(matchId: String): Flow<List<PostMatchThread>>
    fun getComments(threadId: String): Flow<List<Comment>>
    suspend fun postComment(threadId: String, body: String, userName: String, userTeamId: String)
    suspend fun upvoteThread(threadId: String)
}

class MockThreadRepository : ThreadRepository {
    private val threadsFlow = MutableStateFlow(MockCricketData.threads)
    private val commentsFlow = MutableStateFlow(MockCricketData.comments)
    private var nextCommentId = 100

    override fun getThreadsForMatch(matchId: String): Flow<List<PostMatchThread>> =
        threadsFlow.map { threads -> threads.filter { it.matchId == matchId } }

    override fun getComments(threadId: String): Flow<List<Comment>> =
        commentsFlow.map { comments -> comments.filter { it.threadId == threadId } }

    override suspend fun postComment(threadId: String, body: String, userName: String, userTeamId: String) {
        val comment = Comment(
            id = "cm${nextCommentId++}",
            threadId = threadId,
            userId = "current_user",
            userName = userName,
            userTeamId = userTeamId,
            body = body,
            timestamp = "2026-04-18T23:00:00+05:30"
        )
        commentsFlow.value = commentsFlow.value + comment
        threadsFlow.value = threadsFlow.value.map {
            if (it.id == threadId) it.copy(commentCount = it.commentCount + 1) else it
        }
    }

    override suspend fun upvoteThread(threadId: String) {
        threadsFlow.value = threadsFlow.value.map {
            if (it.id == threadId) it.copy(upvotes = it.upvotes + 1) else it
        }
    }
}
