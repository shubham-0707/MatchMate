package com.shubham.matchmate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.ui.components.H2HStatsCard
import com.shubham.matchmate.viewmodel.TimelineViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeadToHeadScreen(
    team1Id: String,
    team2Id: String,
    onBack: () -> Unit,
    viewModel: TimelineViewModel = koinViewModel()
) {
    val h2h by viewModel.headToHead.collectAsState()

    LaunchedEffect(team1Id, team2Id) {
        viewModel.loadHeadToHead(team1Id, team2Id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Head to Head \u2694\uFE0F", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { padding ->
        h2h?.let { data ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                H2HStatsCard(h2h = data)
            }
        } ?: Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        }
    }
}
