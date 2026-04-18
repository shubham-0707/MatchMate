package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.ChatMessage
import com.shubham.matchmate.data.model.Match
import com.shubham.matchmate.data.model.Poll
import com.shubham.matchmate.data.repository.ChatRepository
import com.shubham.matchmate.data.repository.MatchRepository
import com.shubham.matchmate.data.repository.PollRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MatchDetailViewModel(
    private val matchRepo: MatchRepository,
    private val chatRepo: ChatRepository,
    private val pollRepo: PollRepository,
) : ViewModel() {

    private val _match = MutableStateFlow<Match?>(null)
    val match: StateFlow<Match?> = _match.asStateFlow()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _polls = MutableStateFlow<List<Poll>>(emptyList())
    val polls: StateFlow<List<Poll>> = _polls.asStateFlow()

    private val _reactionBurst = MutableSharedFlow<String>()
    val reactionBurst: SharedFlow<String> = _reactionBurst.asSharedFlow()

    val selectedTab = MutableStateFlow(0)

    fun loadMatch(matchId: String) {
        viewModelScope.launch {
            matchRepo.getMatchById(matchId).collect { _match.value = it }
        }
        viewModelScope.launch {
            chatRepo.getMessages(matchId).collect { _messages.value = it }
        }
        viewModelScope.launch {
            pollRepo.getPollsForMatch(matchId).collect { _polls.value = it }
        }
    }

    fun sendMessage(matchId: String, message: String) {
        viewModelScope.launch {
            chatRepo.sendMessage(matchId, message, "CricketFan42", "mi")
        }
    }

    fun sendReaction(emoji: String) {
        viewModelScope.launch {
            _reactionBurst.emit(emoji)
        }
    }

    fun votePoll(pollId: String, optionId: String) {
        viewModelScope.launch {
            pollRepo.vote(pollId, optionId)
        }
    }
}
