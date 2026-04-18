package com.shubham.matchmate.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Prediction(
    val id: String,
    val matchId: String,
    val userId: String,
    val type: PredictionType,
    val choice: String,
    val isCorrect: Boolean? = null,
    val pointsEarned: Int = 0
)

@Serializable
enum class PredictionType {
    MATCH_WINNER, TOP_SCORER, TOTAL_RUNS, FIRST_WICKET_OVER
}

@Serializable
data class PredictionQuestion(
    val id: String,
    val matchId: String,
    val type: PredictionType,
    val question: String,
    val options: List<String>,
    val correctAnswer: String? = null,
    val totalPredictions: Int = 0,
    val predictionBreakdown: Map<String, Int> = emptyMap()
)

fun PredictionType.label(): String = when (this) {
    PredictionType.MATCH_WINNER -> "\uD83C\uDFC6 Match Winner"
    PredictionType.TOP_SCORER -> "\u2B50 Top Scorer"
    PredictionType.TOTAL_RUNS -> "\uD83C\uDFCF Total Runs"
    PredictionType.FIRST_WICKET_OVER -> "\uD83C\uDFAF First Wicket Over"
}

fun PredictionType.points(): Int = when (this) {
    PredictionType.MATCH_WINNER -> 10
    PredictionType.TOP_SCORER -> 25
    PredictionType.TOTAL_RUNS -> 15
    PredictionType.FIRST_WICKET_OVER -> 20
}
