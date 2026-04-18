package com.shubham.matchmate.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shubham.matchmate.data.repository.UserRepository
import com.shubham.matchmate.ui.components.TeamLogo
import com.shubham.matchmate.ui.theme.getTeamColor
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun TeamSelectionScreen(
    onTeamSelected: () -> Unit,
    userRepo: UserRepository = koinInject()
) {
    val teams = remember { userRepo.getTeams() }
    var selectedTeamId by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            if (selectedTeamId != null) {
                ExtendedFloatingActionButton(
                    onClick = {
                        scope.launch {
                            userRepo.selectFavoriteTeam(selectedTeamId!!)
                            onTeamSelected()
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Text("Continue \u2192", fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Pick Your Team \uD83C\uDFCF",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Choose the team you support",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(teams) { team ->
                    val isSelected = selectedTeamId == team.id
                    val teamColor = getTeamColor(team.id)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.85f)
                            .clickable { selectedTeamId = team.id }
                            .then(
                                if (isSelected) Modifier.border(3.dp, teamColor, RoundedCornerShape(16.dp))
                                else Modifier
                            ),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) teamColor.copy(alpha = 0.15f)
                            else MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            TeamLogo(teamId = team.id, teamName = team.shortName, size = 56.dp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = team.shortName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) teamColor else MaterialTheme.colorScheme.onSurface,
                                maxLines = 1
                            )
                            Text(
                                text = team.name,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}
