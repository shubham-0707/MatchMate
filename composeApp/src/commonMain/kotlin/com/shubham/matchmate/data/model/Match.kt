package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val id: String,
    val team1: Team,
    val team2: Team,
    val venue: String,
    val startTime: String,
    val status: MatchStatus,
    val scoreTeam1: InningsScore? = null,
    val scoreTeam2: InningsScore? = null,
    val currentBatsmen: List<BatsmanScore> = emptyList(),
    val currentBowler: BowlerScore? = null,
    val tossWinner: String? = null,
    val tossDecision: String? = null,
    val result: String? = null
)

@Serializable
data class InningsScore(
    val runs: Int,
    val wickets: Int,
    val overs: Float
)

@Serializable
data class BatsmanScore(
    val playerName: String,
    val runs: Int,
    val balls: Int,
    val fours: Int,
    val sixes: Int,
    val isOnStrike: Boolean = false
)

@Serializable
data class BowlerScore(
    val playerName: String,
    val overs: Float,
    val maidens: Int,
    val runs: Int,
    val wickets: Int
)

@Serializable
enum class MatchStatus {
    UPCOMING, LIVE, COMPLETED
}
