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
import com.shubham.matchmate.data.model.FanLevel
import com.shubham.matchmate.ui.components.LeaderboardRow
import com.shubham.matchmate.ui.components.XpProgressBar
import com.shubham.matchmate.viewmodel.LeaderboardViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    matchId: String? = null,
    onBack: () -> Unit,
    viewModel: LeaderboardViewModel = koinViewModel()
) {
    val globalLeaderboard by viewModel.globalLeaderboard.collectAsState()
    val matchLeaderboard by viewModel.matchLeaderboard.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()

    LaunchedEffect(matchId) {
        matchId?.let { viewModel.loadMatchLeaderboard(it) }
    }

    val tabs = if (matchId != null) listOf("Season", "This Match") else listOf("Season")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Leaderboard \uD83C\uDFC6", fontWeight = FontWeight.Bold) },
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
            // Current user XP card
            val currentUser = globalLeaderboard.find { it.isCurrentUser }
            if (currentUser != null) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Your Rank: ", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                "#${currentUser.rank}",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        XpProgressBar(currentXp = currentUser.xp, level = currentUser.level)
                    }
                }
            }

            // Tabs
            if (tabs.size > 1) {
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
            }

            val leaderboard = if (selectedTab == 0) globalLeaderboard else matchLeaderboard

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(leaderboard, key = { it.userId }) { entry ->
                    LeaderboardRow(entry = entry)
                }
            }
        }
    }
}
