package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Poll(
    val id: String,
    val matchId: String,
    val question: String,
    val options: List<PollOption>,
    val createdBy: String = "",
    val expiresAt: String = "",
    val totalVotes: Int = 0
)

@Serializable
data class PollOption(
    val id: String,
    val text: String,
    val votes: Int = 0
)
