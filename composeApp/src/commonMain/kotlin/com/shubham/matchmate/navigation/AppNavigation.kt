package com.shubham.matchmate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shubham.matchmate.ui.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.Splash) {
        composable<Route.Splash> {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Route.Login) {
                        popUpTo(Route.Splash) { inclusive = true }
                    }
                }
            )
        }

        composable<Route.Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Route.TeamSelection) {
                        popUpTo(Route.Login) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Route.SignUp)
                }
            )
        }

        composable<Route.SignUp> {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate(Route.TeamSelection) {
                        popUpTo(Route.Login) { inclusive = true }
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable<Route.TeamSelection> {
            TeamSelectionScreen(
                onTeamSelected = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.TeamSelection) { inclusive = true }
                    }
                }
            )
        }

        composable<Route.Home> {
            HomeScreen(
                onMatchClick = { matchId -> navController.navigate(Route.MatchDetail(matchId)) },
                onProfileClick = { navController.navigate(Route.Profile) },
                onCompletedMatchClick = { matchId -> navController.navigate(Route.PostMatchThreads(matchId)) }
            )
        }

        composable<Route.MatchDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.MatchDetail>()
            MatchDetailScreen(
                matchId = route.matchId,
                onBack = { navController.popBackStack() }
            )
        }

        composable<Route.Profile> {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate(Route.Login) {
                        popUpTo(Route.Home) { inclusive = true }
                    }
                },
                onSettings = { navController.navigate(Route.Settings) }
            )
        }

        composable<Route.PostMatchThreads> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.PostMatchThreads>()
            PostMatchThreadsScreen(
                matchId = route.matchId,
                onBack = { navController.popBackStack() },
                onThreadClick = { threadId ->
                    navController.navigate(Route.ThreadDetail(threadId, route.matchId))
                }
            )
        }

        composable<Route.ThreadDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<Route.ThreadDetail>()
            ThreadDetailScreen(
                threadId = route.threadId,
                matchId = route.matchId,
                onBack = { navController.popBackStack() }
            )
        }

        composable<Route.Settings> {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
