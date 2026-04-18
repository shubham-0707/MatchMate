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
import com.shubham.matchmate.data.model.isHighlight
import com.shubham.matchmate.ui.components.TimelineEventCard
import com.shubham.matchmate.viewmodel.TimelineViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchTimelineScreen(
    matchId: String,
    onBack: () -> Unit,
    viewModel: TimelineViewModel = koinViewModel()
) {
    val events by viewModel.events.collectAsState()
    val showHighlightsOnly by viewModel.showHighlightsOnly.collectAsState()

    LaunchedEffect(matchId) { viewModel.loadTimeline(matchId) }

    val displayEvents = if (showHighlightsOnly) events.filter { it.type.isHighlight() } else events

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ball-by-Ball \uD83C\uDFCF", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Filter chips
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = !showHighlightsOnly,
                    onClick = { if (showHighlightsOnly) viewModel.toggleHighlightsOnly() },
                    label = { Text("All Events") }
                )
                FilterChip(
                    selected = showHighlightsOnly,
                    onClick = { if (!showHighlightsOnly) viewModel.toggleHighlightsOnly() },
                    label = { Text("\uD83D\uDD25 Highlights Only") }
                )
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        text = "${displayEvents.size} events",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (displayEvents.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("\uD83C\uDFCF", style = MaterialTheme.typography.headlineLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("No events yet for this match", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(displayEvents.reversed(), key = { it.id }) { event ->
                        TimelineEventCard(event = event)
                    }
                }
            }
        }
    }
}
