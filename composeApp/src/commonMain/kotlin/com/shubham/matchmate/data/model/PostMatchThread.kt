package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostMatchThread(
    val id: String,
    val matchId: String,
    val title: String,
    val body: String,
    val authorId: String,
    val authorName: String,
    val createdAt: String,
    val commentCount: Int = 0,
    val upvotes: Int = 0
)

@Serializable
data class Comment(
    val id: String,
    val threadId: String,
    val userId: String,
    val userName: String,
    val userTeamId: String = "",
    val body: String,
    val timestamp: String,
    val upvotes: Int = 0
)
