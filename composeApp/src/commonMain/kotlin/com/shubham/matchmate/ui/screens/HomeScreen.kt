package com.shubham.matchmate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.model.MatchStatus
import com.shubham.matchmate.ui.components.LiveScoreCard
import com.shubham.matchmate.ui.components.TeamLogo
import com.shubham.matchmate.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMatchClick: (String) -> Unit,
    onProfileClick: () -> Unit,
    onCompletedMatchClick: (String) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val liveMatches by viewModel.liveMatches.collectAsState()
    val upcomingMatches by viewModel.upcomingMatches.collectAsState()
    val completedMatches by viewModel.completedMatches.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()

    val tabs = listOf("Live", "Upcoming", "Results")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "MatchMate \uD83C\uDFCF",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        TeamLogo(teamId = "mi", teamName = "MI", size = 32.dp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { viewModel.selectedTab.value = index },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                if (index == 0 && liveMatches.isNotEmpty()) {
                                    Badge(containerColor = MaterialTheme.colorScheme.error) {
                                        Text("${liveMatches.size}")
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                                Text(title, fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal)
                            }
                        }
                    )
                }
            }

            val matches = when (selectedTab) {
                0 -> liveMatches
                1 -> upcomingMatches
                2 -> completedMatches
                else -> emptyList()
            }

            if (matches.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = when (selectedTab) {
                                0 -> "\uD83D\uDCFA"
                                1 -> "\uD83D\uDCC5"
                                else -> "\uD83C\uDFC6"
                            },
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = when (selectedTab) {
                                0 -> "No live matches right now"
                                1 -> "No upcoming matches"
                                else -> "No completed matches"
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(matches, key = { it.id }) { match ->
                        LiveScoreCard(
                            match = match,
                            onClick = {
                                if (match.status == MatchStatus.COMPLETED) {
                                    onCompletedMatchClick(match.id)
                                } else {
                                    onMatchClick(match.id)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
