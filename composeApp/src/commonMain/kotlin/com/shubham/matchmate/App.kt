package com.shubham.matchmate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.shubham.matchmate.di.appModule
import com.shubham.matchmate.navigation.AppNavigation
import com.shubham.matchmate.ui.theme.MatchMateTheme
import com.shubham.matchmate.ui.theme.ThemeManager
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        val isDark by ThemeManager.isDarkTheme.collectAsState()
        MatchMateTheme(darkTheme = isDark) {
            AppNavigation()
        }
    }
}
