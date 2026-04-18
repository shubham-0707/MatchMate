package com.shubham.matchmate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.ui.components.ThreadCard
import com.shubham.matchmate.viewmodel.ThreadsViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostMatchThreadsScreen(
    matchId: String,
    onBack: () -> Unit,
    onThreadClick: (String) -> Unit,
    viewModel: ThreadsViewModel = koinViewModel()
) {
    val threads by viewModel.threads.collectAsState()

    LaunchedEffect(matchId) { viewModel.loadThreads(matchId) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Post-Match Discussion", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        if (threads.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("\uD83D\uDCAC", style = MaterialTheme.typography.headlineLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("No threads yet for this match", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(threads, key = { it.id }) { thread ->
                    ThreadCard(
                        thread = thread,
                        onUpvote = { viewModel.upvoteThread(thread.id) },
                        onClick = { onThreadClick(thread.id) }
                    )
                }
            }
        }
    }
}
