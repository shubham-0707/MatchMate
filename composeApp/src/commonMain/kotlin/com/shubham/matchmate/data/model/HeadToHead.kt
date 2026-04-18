package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HeadToHead(
    val team1Id: String,
    val team2Id: String,
    val totalMatches: Int,
    val team1Wins: Int,
    val team2Wins: Int,
    val noResults: Int = 0,
    val team1AvgScore: Int,
    val team2AvgScore: Int,
    val team1HighestScore: Int,
    val team2HighestScore: Int,
    val team1BiggestWin: String,
    val team2BiggestWin: String,
    val lastFiveResults: List<H2HResult>,
    val topPerformers: List<H2HTopPerformer> = emptyList()
)

@Serializable
data class H2HResult(
    val matchId: String,
    val winnerId: String,
    val result: String,
    val date: String
)

@Serializable
data class H2HTopPerformer(
    val playerName: String,
    val teamId: String,
    val stat: String
)
