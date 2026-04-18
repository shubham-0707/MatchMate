package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MatchEvent(
    val id: String,
    val matchId: String,
    val over: Float,
    val ball: Int,
    val type: EventType,
    val title: String,
    val description: String,
    val playerName: String = "",
    val runs: Int = 0,
    val timestamp: String
)

@Serializable
enum class EventType {
    DOT_BALL, SINGLE, DOUBLE, TRIPLE, FOUR, SIX, WICKET,
    WIDE, NO_BALL, FREE_HIT, MAIDEN_OVER,
    FIFTY, CENTURY, DRINKS_BREAK, INNINGS_BREAK, TOSS
}

fun EventType.emoji(): String = when (this) {
    EventType.DOT_BALL -> "\u26AA"
    EventType.SINGLE -> "\u25B6\uFE0F"
    EventType.DOUBLE -> "\u23E9"
    EventType.TRIPLE -> "\uD83C\uDFC3"
    EventType.FOUR -> "\uD83D\uDFE2"
    EventType.SIX -> "\uD83D\uDFE1"
    EventType.WICKET -> "\uD83D\uDD34"
    EventType.WIDE -> "\u27A1\uFE0F"
    EventType.NO_BALL -> "\u274C"
    EventType.FREE_HIT -> "\uD83C\uDD93"
    EventType.MAIDEN_OVER -> "\uD83D\uDEE1\uFE0F"
    EventType.FIFTY -> "\u2B50"
    EventType.CENTURY -> "\uD83D\uDCAF"
    EventType.DRINKS_BREAK -> "\u2615"
    EventType.INNINGS_BREAK -> "\u23F8\uFE0F"
    EventType.TOSS -> "\uD83E\uDE99"
}

fun EventType.isHighlight(): Boolean = this in listOf(
    EventType.FOUR, EventType.SIX, EventType.WICKET,
    EventType.FIFTY, EventType.CENTURY, EventType.FREE_HIT
)
