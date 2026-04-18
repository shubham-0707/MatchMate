package com.shubham.matchmate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.MatchStatus
import com.shubham.matchmate.ui.components.*
import com.shubham.matchmate.viewmodel.MatchDetailViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(
    matchId: String,
    onBack: () -> Unit,
    onTimelineClick: () -> Unit = {},
    onLeaderboardClick: () -> Unit = {},
    onPredictionsClick: () -> Unit = {},
    onH2HClick: (String, String) -> Unit = { _, _ -> },
    viewModel: MatchDetailViewModel = koinViewModel()
) {
    val match by viewModel.match.collectAsState()
    val messages by viewModel.messages.collectAsState()
    val polls by viewModel.polls.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()

    LaunchedEffect(matchId) {
        viewModel.loadMatch(matchId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    match?.let {
                        Text(
                            "${it.team1.shortName} vs ${it.team2.shortName}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        match?.let { m ->
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                // Score header
                LiveScoreCard(match = m, modifier = Modifier.padding(horizontal = 16.dp))

                // Quick Action Buttons for new features
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    QuickActionChip(
                        icon = Icons.Filled.Timeline,
                        label = "Timeline",
                        onClick = onTimelineClick,
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionChip(
                        icon = Icons.Filled.Leaderboard,
                        label = "Ranks",
                        onClick = onLeaderboardClick,
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionChip(
                        icon = Icons.Filled.EmojiEvents,
                        label = "Predict",
                        onClick = onPredictionsClick,
                        modifier = Modifier.weight(1f)
                    )
                    QuickActionChip(
                        icon = Icons.Filled.CompareArrows,
                        label = "H2H",
                        onClick = { onH2HClick(m.team1.id, m.team2.id) },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Current batsmen & bowler
                if (m.status == MatchStatus.LIVE && m.currentBatsmen.isNotEmpty()) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            // Header
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Batter", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(1.5f))
                                Text("R", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                                Text("B", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                                Text("4s", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                                Text("6s", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                            }
                            m.currentBatsmen.forEach { BatsmanRow(it) }
                            Spacer(modifier = Modifier.height(8.dp))
                            HorizontalDivider()
                            Spacer(modifier = Modifier.height(8.dp))
                            m.currentBowler?.let {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text("Bowler", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(1.5f))
                                    Text("O", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                                    Text("M", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                                    Text("R", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                                    Text("W", style = MaterialTheme.typography.labelSmall, modifier = Modifier.weight(0.5f))
                                }
                                BowlerRow(it)
                            }
                        }
                    }
                }

                // Tabs
                val tabs = listOf("Chat", "Polls", "Info")
                TabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { viewModel.selectedTab.value = index },
                            text = { Text(title, fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal) }
                        )
                    }
                }

                // Content
                Box(modifier = Modifier.weight(1f)) {
                    when (selectedTab) {
                        0 -> {
                            // Chat tab
                            Box(modifier = Modifier.fillMaxSize()) {
                                val listState = rememberLazyListState()
                                LaunchedEffect(messages.size) {
                                    if (messages.isNotEmpty()) listState.animateScrollToItem(messages.size - 1)
                                }

                                Column(modifier = Modifier.fillMaxSize()) {
                                    LazyColumn(
                                        state = listState,
                                        modifier = Modifier.weight(1f),
                                        contentPadding = PaddingValues(vertical = 8.dp)
                                    ) {
                                        items(messages, key = { it.id }) { msg ->
                                            ChatBubble(
                                                message = msg,
                                                isCurrentUser = msg.userId == "current_user"
                                            )
                                        }
                                    }
                                    ChatInput(
                                        onSendMessage = { viewModel.sendMessage(matchId, it) },
                                        onReaction = { viewModel.sendReaction(it) }
                                    )
                                }

                                // Floating reactions
                                ReactionOverlay(
                                    reactionBurst = viewModel.reactionBurst,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                        1 -> {
                            // Polls tab
                            if (polls.isEmpty()) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text("No polls yet", color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            } else {
                                LazyColumn(
                                    contentPadding = PaddingValues(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    items(polls, key = { it.id }) { poll ->
                                        PollCard(
                                            poll = poll,
                                            onVote = { pollId, optionId -> viewModel.votePoll(pollId, optionId) }
                                        )
                                    }
                                }
                            }
                        }
                        2 -> {
                            // Info tab
                            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                                item {
                                    Card(
                                        modifier = Modifier.fillMaxWidth(),
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text("Match Info", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                            Spacer(modifier = Modifier.height(12.dp))
                                            InfoRow("Venue", m.venue)
                                            InfoRow("Start Time", m.startTime.replace("T", " ").take(16))
                                            m.tossWinner?.let { InfoRow("Toss", "$it won and chose to ${m.tossDecision}") }
                                            m.result?.let { InfoRow("Result", it) }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } ?: Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun QuickActionChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(icon, contentDescription = label, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall, maxLines = 1)
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}
