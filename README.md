# MatchMate 🏏

A **cricket fan connection platform** built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform**. MatchMate enables fans to form meaningful connections around shared team loyalties and live match experiences — centered around IPL cricket.

![Kotlin](https://img.shields.io/badge/Kotlin-2.3.20-7F52FF?logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Compose_Multiplatform-1.10.3-4285F4?logo=jetpackcompose&logoColor=white)
![Platform](https://img.shields.io/badge/Platform-Android_|_iOS-green)

---

## Features

### Core
- **Live Match Feed** — Live, Upcoming, and Completed match tabs with real-time score cards
- **Match Chat Rooms** — Real-time chat per match with team-colored bubbles
- **Emoji Reactions** — Floating emoji burst overlay with tap animations
- **Live Polls** — In-match polls with animated vote percentage bars
- **Fan Profiles & Badges** — Team loyalty stats, earned badges, and fan levels
- **Post-Match Threads** — Discussion threads with nested comments for completed matches
- **Team Selection** — Onboarding flow to pick your favorite IPL team
- **Dark / Light Theme** — Toggle via Settings, persisted with global ThemeManager

### Advanced
- **Live Match Timeline** — Ball-by-ball event feed with highlights-only filter (Sixes, Wickets, Fours)
- **Fan Leaderboard** — XP-based ranking system with levels (Rookie → Legend), Season & Match tabs
- **Match Predictions** — Predict match outcomes before they happen, earn points for correct guesses
- **Head-to-Head Stats** — Historical team comparisons with win bars, top performers, and last 5 results

---

## Screenshots

> Run the app to explore all 14 screens — from the animated Splash screen to the Head-to-Head stats view.

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| **Language** | Kotlin 2.3.20 |
| **UI** | Compose Multiplatform + Material 3 |
| **Architecture** | MVVM + Clean Architecture |
| **DI** | Koin 4.0.4 |
| **Navigation** | Compose Navigation (type-safe `@Serializable` routes) |
| **Networking** | Ktor 3.1.3 |
| **Image Loading** | Coil 3.2.0 |
| **Serialization** | kotlinx-serialization 1.8.1 |
| **Date/Time** | kotlinx-datetime 0.6.2 |
| **Data** | Mock repositories (Firebase-swappable via Koin) |

---

## Project Structure

```
composeApp/src/commonMain/kotlin/com/shubham/matchmate/
├── App.kt                          # Entry point — Koin + Theme + Navigation
├── data/
│   ├── model/                      # 10 data models
│   │   ├── Chat.kt                 # ChatMessage
│   │   ├── HeadToHead.kt           # HeadToHead, H2HResult, H2HTopPerformer
│   │   ├── Leaderboard.kt          # LeaderboardEntry, FanLevel enum
│   │   ├── Match.kt                # Match, InningsScore, MatchStatus
│   │   ├── MatchEvent.kt           # MatchEvent, EventType enum
│   │   ├── Poll.kt                 # Poll, PollOption
│   │   ├── PostMatchThread.kt      # PostMatchThread, Comment
│   │   ├── Prediction.kt           # Prediction, PredictionQuestion
│   │   ├── Team.kt                 # Team, Player, PlayerRole
│   │   └── User.kt                 # FanProfile, Badge
│   ├── remote/                     # Mock data sources
│   │   ├── MockCricketData.kt      # 10 IPL teams, matches, chats, polls
│   │   └── MockNewFeatureData.kt   # Timeline events, leaderboards, H2H
│   └── repository/                 # 8 repository interfaces + mock impls
│       ├── ChatRepository.kt
│       ├── LeaderboardRepository.kt
│       ├── MatchRepository.kt
│       ├── PollRepository.kt
│       ├── PredictionRepository.kt
│       ├── ThreadRepository.kt
│       ├── TimelineRepository.kt
│       └── UserRepository.kt
├── di/
│   └── AppModule.kt                # Koin wiring — 8 repos + 8 ViewModels
├── navigation/
│   ├── AppNavigation.kt            # NavHost with 14 routes
│   └── Routes.kt                   # Type-safe @Serializable route definitions
├── ui/
│   ├── components/                  # 17 reusable composables
│   │   ├── BadgeChip.kt
│   │   ├── BatsmanRow.kt
│   │   ├── BowlerRow.kt
│   │   ├── ChatBubble.kt           # Team-colored chat bubbles
│   │   ├── ChatInput.kt            # Message input + emoji reactions
│   │   ├── CommentItem.kt
│   │   ├── H2HStatsCard.kt         # Animated win bar, stats grid
│   │   ├── LeaderboardRow.kt       # Gold/silver/bronze medals
│   │   ├── LiveScoreCard.kt        # Match score with team logos
│   │   ├── MatchStatusBadge.kt     # Pulsing LIVE indicator
│   │   ├── PollCard.kt             # Animated vote bars
│   │   ├── PredictionCard.kt       # Prediction with % breakdown
│   │   ├── ReactionOverlay.kt      # Floating emoji animations
│   │   ├── TeamLogo.kt
│   │   ├── ThreadCard.kt
│   │   ├── TimelineEventCard.kt    # Color-coded by event type
│   │   └── XpProgressBar.kt        # Animated XP level bar
│   ├── screens/                     # 14 screens
│   │   ├── SplashScreen.kt         # Animated cricket ball + glow rings
│   │   ├── LoginScreen.kt
│   │   ├── SignUpScreen.kt
│   │   ├── TeamSelectionScreen.kt   # IPL team picker grid
│   │   ├── HomeScreen.kt           # Live / Upcoming / Results tabs
│   │   ├── MatchDetailScreen.kt    # Score + Chat + Polls + Info
│   │   ├── MatchTimelineScreen.kt  # Ball-by-ball events
│   │   ├── LeaderboardScreen.kt    # XP rankings
│   │   ├── PredictionScreen.kt     # Match predictions
│   │   ├── HeadToHeadScreen.kt     # Team H2H comparison
│   │   ├── ProfileScreen.kt        # Fan stats & badges
│   │   ├── PostMatchThreadsScreen.kt
│   │   ├── ThreadDetailScreen.kt
│   │   └── SettingsScreen.kt       # Theme toggle
│   └── theme/
│       ├── Color.kt                # Cricket-themed palette + team colors
│       ├── Theme.kt                # Material 3 dark/light schemes
│       ├── ThemeManager.kt         # Global theme state (StateFlow)
│       └── Type.kt                 # Typography
└── viewmodel/                       # 8 ViewModels
    ├── AuthViewModel.kt
    ├── HomeViewModel.kt
    ├── LeaderboardViewModel.kt
    ├── MatchDetailViewModel.kt
    ├── PredictionViewModel.kt
    ├── ProfileViewModel.kt
    ├── ThreadsViewModel.kt
    └── TimelineViewModel.kt
```

---

## Architecture

```
┌─────────────┐     ┌──────────────┐     ┌─────────────────┐
│   Screens    │ ──▶ │  ViewModels  │ ──▶ │  Repositories   │
│  (Compose)   │     │ (StateFlow)  │     │  (Interfaces)   │
└─────────────┘     └──────────────┘     └────────┬────────┘
                                                   │
                                          ┌────────▼────────┐
                                          │  Mock Impls      │
                                          │  (In-Memory)     │
                                          └────────┬────────┘
                                                   │
                                          ┌────────▼────────┐
                                          │  Firebase (TODO) │
                                          │  (Swap via Koin) │
                                          └─────────────────┘
```

All repositories are **interface-based** and wired through **Koin**. To swap from mock data to Firebase, change one line per repository in `AppModule.kt`:

```kotlin
// Mock (current)
singleOf(::MockMatchRepository).bind<MatchRepository>()

// Firebase (future)
singleOf(::FirebaseMatchRepository).bind<MatchRepository>()
```

---

## Navigation Flow

```
Splash ─┬─▶ Login ──▶ Team Selection ──▶ Home
        └─▶ Home (if already logged in)

Home ──▶ Match Detail ─┬─▶ Timeline
     │                 ├─▶ Leaderboard
     │                 ├─▶ Predictions
     │                 └─▶ Head-to-Head
     ├─▶ Profile ──▶ Settings
     └─▶ Post-Match Threads ──▶ Thread Detail
```

---

## Build & Run

### Android

```bash
# macOS / Linux
./gradlew :composeApp:assembleDebug

# Windows
.\gradlew.bat :composeApp:assembleDebug
```

Or use the run configuration in Android Studio / Fleet.

### iOS

Open the `/iosApp` directory in Xcode and run, or use the iOS run configuration in your IDE.

---

## Mock Data

The app ships with realistic **IPL 2026** mock data:

- **10 IPL teams** with full rosters and team colors
- **2 live matches** with ball-by-ball commentary
- **2 completed matches** with results and threads
- **7 upcoming matches** dynamically generated based on your favorite team
- **Chat messages, polls, predictions, leaderboard entries, H2H stats**

---

## Gamification

| Level | XP Required | Emoji |
|-------|------------|-------|
| Rookie | 0 | 🌱 |
| Fan | 100 | ⭐ |
| Super Fan | 500 | 🌟 |
| Ultra Fan | 1500 | 💫 |
| Legend | 5000 | 👑 |

Earn XP by chatting, voting in polls, making predictions, and participating in match threads.

---

## License

This project is for educational and demonstration purposes.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html).
