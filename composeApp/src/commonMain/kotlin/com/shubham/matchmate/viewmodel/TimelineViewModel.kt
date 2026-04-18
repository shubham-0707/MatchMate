package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.HeadToHead
import com.shubham.matchmate.data.model.MatchEvent
import com.shubham.matchmate.data.repository.TimelineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimelineViewModel(private val timelineRepo: TimelineRepository) : ViewModel() {
    private val _events = MutableStateFlow<List<MatchEvent>>(emptyList())
    val events: StateFlow<List<MatchEvent>> = _events.asStateFlow()

    private val _headToHead = MutableStateFlow<HeadToHead?>(null)
    val headToHead: StateFlow<HeadToHead?> = _headToHead.asStateFlow()

    private val _showHighlightsOnly = MutableStateFlow(false)
    val showHighlightsOnly: StateFlow<Boolean> = _showHighlightsOnly.asStateFlow()

    fun loadTimeline(matchId: String) {
        viewModelScope.launch {
            timelineRepo.getTimelineEvents(matchId).collect { _events.value = it }
        }
    }

    fun loadHeadToHead(team1Id: String, team2Id: String) {
        viewModelScope.launch {
            timelineRepo.getHeadToHead(team1Id, team2Id).collect { _headToHead.value = it }
        }
    }

    fun toggleHighlightsOnly() {
        _showHighlightsOnly.value = !_showHighlightsOnly.value
    }
}
