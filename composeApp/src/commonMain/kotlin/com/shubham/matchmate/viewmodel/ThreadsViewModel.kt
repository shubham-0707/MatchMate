package com.shubham.matchmate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shubham.matchmate.data.model.Comment
import com.shubham.matchmate.data.model.PostMatchThread
import com.shubham.matchmate.data.repository.ThreadRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ThreadsViewModel(private val threadRepo: ThreadRepository) : ViewModel() {
    private val _threads = MutableStateFlow<List<PostMatchThread>>(emptyList())
    val threads: StateFlow<List<PostMatchThread>> = _threads.asStateFlow()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    fun loadThreads(matchId: String) {
        viewModelScope.launch {
            threadRepo.getThreadsForMatch(matchId).collect { _threads.value = it }
        }
    }

    fun loadComments(threadId: String) {
        viewModelScope.launch {
            threadRepo.getComments(threadId).collect { _comments.value = it }
        }
    }

    fun postComment(threadId: String, body: String) {
        viewModelScope.launch {
            threadRepo.postComment(threadId, body, "CricketFan42", "mi")
        }
    }

    fun upvoteThread(threadId: String) {
        viewModelScope.launch {
            threadRepo.upvoteThread(threadId)
        }
    }
}
