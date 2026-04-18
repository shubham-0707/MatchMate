package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.Prediction
import com.shubham.matchmate.data.model.PredictionQuestion
import com.shubham.matchmate.data.repository.PredictionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PredictionViewModel(private val predictionRepo: PredictionRepository) : ViewModel() {
    private val _questions = MutableStateFlow<List<PredictionQuestion>>(emptyList())
    val questions: StateFlow<List<PredictionQuestion>> = _questions.asStateFlow()

    private val _userPredictions = MutableStateFlow<List<Prediction>>(emptyList())
    val userPredictions: StateFlow<List<Prediction>> = _userPredictions.asStateFlow()

    fun loadPredictions(matchId: String) {
        viewModelScope.launch {
            predictionRepo.getPredictionQuestions(matchId).collect { _questions.value = it }
        }
        viewModelScope.launch {
            predictionRepo.getUserPredictions(matchId).collect { _userPredictions.value = it }
        }
    }

    fun submitPrediction(matchId: String, questionId: String, choice: String) {
        viewModelScope.launch {
            predictionRepo.submitPrediction(matchId, questionId, choice)
        }
    }
}
