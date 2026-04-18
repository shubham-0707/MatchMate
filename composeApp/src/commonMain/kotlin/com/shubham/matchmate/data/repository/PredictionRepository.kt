package com.shubham.matchmate.data.repository

import com.shubham.matchmate.data.model.Prediction
import com.shubham.matchmate.data.model.PredictionQuestion
import com.shubham.matchmate.data.remote.MockNewFeatureData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface PredictionRepository {
    fun getPredictionQuestions(matchId: String): Flow<List<PredictionQuestion>>
    fun getUserPredictions(matchId: String): Flow<List<Prediction>>
    suspend fun submitPrediction(matchId: String, questionId: String, choice: String)
}

class MockPredictionRepository : PredictionRepository {
    private val predictionsFlow = MutableStateFlow<List<Prediction>>(emptyList())
    private val questionsFlow = MutableStateFlow<Map<String, List<PredictionQuestion>>>(emptyMap())
    private var nextId = 1

    override fun getPredictionQuestions(matchId: String): Flow<List<PredictionQuestion>> {
        // Initialize if not present
        if (questionsFlow.value[matchId] == null) {
            val questions = MockNewFeatureData.getPredictionQuestions(matchId)
            questionsFlow.value = questionsFlow.value + (matchId to questions)
        }
        return questionsFlow.map { it[matchId] ?: emptyList() }
    }

    override fun getUserPredictions(matchId: String): Flow<List<Prediction>> =
        predictionsFlow.map { preds -> preds.filter { it.matchId == matchId } }

    override suspend fun submitPrediction(matchId: String, questionId: String, choice: String) {
        // Check if already predicted
        if (predictionsFlow.value.any { it.matchId == matchId && it.id == questionId }) return

        val prediction = Prediction(
            id = questionId,
            matchId = matchId,
            userId = "current_user",
            type = com.shubham.matchmate.data.model.PredictionType.MATCH_WINNER,
            choice = choice
        )
        predictionsFlow.value = predictionsFlow.value + prediction

        // Update question breakdown
        questionsFlow.value = questionsFlow.value.mapValues { (mid, questions) ->
            if (mid == matchId) {
                questions.map { q ->
                    if (q.id == questionId) {
                        q.copy(
                            totalPredictions = q.totalPredictions + 1,
                            predictionBreakdown = q.predictionBreakdown.mapValues { (opt, count) ->
                                if (opt == choice) count + 1 else count
                            }
                        )
                    } else q
                }
            } else questions
        }
    }
}
