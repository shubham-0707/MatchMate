package com.shubham.matchmate.data.remote

import com.shubham.matchmate.data.model.*

object MockNewFeatureData {

    // ==================== MATCH TIMELINE ====================
    fun getTimelineEvents(matchId: String): List<MatchEvent> {
        if (matchId == "m1") return miVsCskTimeline
        if (matchId == "m2") return rcbVsKkrTimeline
        return emptyList()
    }

    private val miVsCskTimeline = listOf(
        MatchEvent("e1", "m1", 0.0f, 0, EventType.TOSS, "MI won the toss", "Mumbai Indians won the toss and elected to bat", timestamp = "2026-04-18T19:30:00"),
        MatchEvent("e2", "m1", 0.4f, 4, EventType.FOUR, "FOUR!", "Rohit Sharma drives through covers", "Rohit Sharma", 4, "2026-04-18T19:38:00"),
        MatchEvent("e3", "m1", 1.1f, 1, EventType.SIX, "SIX! Massive!", "Rohit Sharma pulls over deep mid-wicket, into the stands!", "Rohit Sharma", 6, "2026-04-18T19:42:00"),
        MatchEvent("e4", "m1", 2.3f, 3, EventType.WICKET, "WICKET! Caught!", "Ishan Kishan caught at slip off Deepak Chahar", "Ishan Kishan", 0, "2026-04-18T19:50:00"),
        MatchEvent("e5", "m1", 3.5f, 5, EventType.FOUR, "FOUR!", "SKY flicks off the pads, races to boundary", "Suryakumar Yadav", 4, "2026-04-18T19:58:00"),
        MatchEvent("e6", "m1", 5.6f, 6, EventType.SIX, "SIX! Into the crowd!", "Rohit Sharma goes downtown, massive hit over long-on", "Rohit Sharma", 6, "2026-04-18T20:05:00"),
        MatchEvent("e7", "m1", 6.0f, 0, EventType.DRINKS_BREAK, "Strategic Timeout", "MI 52/1 after 6 overs. Powerplay done.", timestamp = "2026-04-18T20:08:00"),
        MatchEvent("e8", "m1", 7.2f, 2, EventType.FOUR, "FOUR!", "SKY cuts brilliantly past point", "Suryakumar Yadav", 4, "2026-04-18T20:12:00"),
        MatchEvent("e9", "m1", 8.4f, 4, EventType.SIX, "SIX! Monster hit!", "Rohit Sharma slog-sweeps Jadeja for six", "Rohit Sharma", 6, "2026-04-18T20:18:00"),
        MatchEvent("e10", "m1", 9.6f, 6, EventType.FIFTY, "\u2B50 FIFTY for Rohit!", "Rohit Sharma reaches his half-century off 32 balls", "Rohit Sharma", 0, "2026-04-18T20:22:00"),
        MatchEvent("e11", "m1", 10.1f, 1, EventType.WICKET, "WICKET! Bowled!", "Tilak Varma bowled by a beauty from Jadeja", "Tilak Varma", 0, "2026-04-18T20:26:00"),
        MatchEvent("e12", "m1", 12.3f, 3, EventType.SIX, "SIX! Over long-off!", "SKY launches Theekshana over long-off", "Suryakumar Yadav", 6, "2026-04-18T20:35:00"),
        MatchEvent("e13", "m1", 13.5f, 5, EventType.FOUR, "FOUR! Edge and away!", "Rohit edges through the slip cordon for four", "Rohit Sharma", 4, "2026-04-18T20:40:00"),
        MatchEvent("e14", "m1", 14.2f, 2, EventType.WICKET, "WICKET! LBW!", "Hardik Pandya trapped LBW by Jadeja", "Hardik Pandya", 0, "2026-04-18T20:45:00"),
        MatchEvent("e15", "m1", 15.4f, 4, EventType.FREE_HIT, "FREE HIT!", "No ball from Pathirana, free hit coming up!", runs = 0, timestamp = "2026-04-18T20:50:00"),
        MatchEvent("e16", "m1", 15.4f, 4, EventType.SIX, "SIX off Free Hit!", "SKY smashes the free hit over mid-wicket!", "Suryakumar Yadav", 6, "2026-04-18T20:50:30"),
        MatchEvent("e17", "m1", 16.1f, 1, EventType.WICKET, "WICKET! Caught!", "Tim David holes out to deep mid-wicket", "Tim David", 0, "2026-04-18T20:55:00"),
    )

    private val rcbVsKkrTimeline = listOf(
        MatchEvent("e20", "m2", 0.0f, 0, EventType.TOSS, "RCB won the toss", "Royal Challengers elected to bat first", timestamp = "2026-04-18T15:30:00"),
        MatchEvent("e21", "m2", 1.3f, 3, EventType.SIX, "SIX!", "Virat Kohli lofts over extra cover", "Virat Kohli", 6, "2026-04-18T15:40:00"),
        MatchEvent("e22", "m2", 3.2f, 2, EventType.FOUR, "FOUR!", "Faf du Plessis cuts past point", "Faf du Plessis", 4, "2026-04-18T15:50:00"),
        MatchEvent("e23", "m2", 6.0f, 0, EventType.DRINKS_BREAK, "Powerplay done", "RCB 58/0 after powerplay", timestamp = "2026-04-18T16:00:00"),
        MatchEvent("e24", "m2", 8.5f, 5, EventType.FIFTY, "\u2B50 FIFTY for Kohli!", "Virat Kohli reaches 50 off 29 balls", "Virat Kohli", 0, "2026-04-18T16:12:00"),
        MatchEvent("e25", "m2", 10.2f, 2, EventType.WICKET, "WICKET!", "Faf caught at deep square leg off Narine", "Faf du Plessis", 0, "2026-04-18T16:20:00"),
        MatchEvent("e26", "m2", 15.3f, 3, EventType.SIX, "SIX! Massive!", "Glenn Maxwell launches into the stands", "Glenn Maxwell", 6, "2026-04-18T16:42:00"),
        MatchEvent("e27", "m2", 18.1f, 1, EventType.FOUR, "FOUR!", "Dinesh Karthik finishes with a sweep", "Dinesh Karthik", 4, "2026-04-18T16:55:00"),
        MatchEvent("e28", "m2", 20.0f, 0, EventType.INNINGS_BREAK, "Innings Break", "RCB posted 198/5 in 20 overs. KKR need 199 to win!", timestamp = "2026-04-18T17:00:00"),
        MatchEvent("e29", "m2", 1.4f, 4, EventType.SIX, "SIX! Russell power!", "Andre Russell smashes it out of the ground", "Andre Russell", 6, "2026-04-18T17:20:00"),
        MatchEvent("e30", "m2", 5.2f, 2, EventType.WICKET, "WICKET! Huge!", "Sunil Narine bowled by Siraj!", "Sunil Narine", 0, "2026-04-18T17:35:00"),
    )

    // ==================== LEADERBOARD ====================
    val globalLeaderboard = listOf(
        LeaderboardEntry(1, "u20", "CricketKing99", "csk", 4850, FanLevel.ULTRA_FAN, 45, 890, 32),
        LeaderboardEntry(2, "u21", "IPLManiac", "mi", 4200, FanLevel.ULTRA_FAN, 42, 750, 28),
        LeaderboardEntry(3, "u22", "SixerQueen", "rcb", 3800, FanLevel.SUPER_FAN, 38, 620, 25),
        LeaderboardEntry(4, "u23", "BoundaryBoss", "kkr", 3100, FanLevel.SUPER_FAN, 35, 510, 22),
        LeaderboardEntry(5, "current_user", "CricketFan42", "mi", 2750, FanLevel.SUPER_FAN, 28, 156, 18, isCurrentUser = true),
        LeaderboardEntry(6, "u24", "WhistlePodu", "csk", 2400, FanLevel.SUPER_FAN, 30, 380, 15),
        LeaderboardEntry(7, "u25", "RCBForever", "rcb", 2100, FanLevel.SUPER_FAN, 27, 340, 14),
        LeaderboardEntry(8, "u26", "KKRRider", "kkr", 1800, FanLevel.SUPER_FAN, 25, 280, 12),
        LeaderboardEntry(9, "u27", "DelhiDaredevil", "dc", 1500, FanLevel.SUPER_FAN, 22, 230, 10),
        LeaderboardEntry(10, "u28", "OrangeArmy", "srh", 1200, FanLevel.FAN, 20, 190, 8),
        LeaderboardEntry(11, "u29", "PunjabDaPutt", "pbks", 950, FanLevel.FAN, 18, 160, 7),
        LeaderboardEntry(12, "u30", "RoyalFan", "rr", 800, FanLevel.FAN, 15, 130, 6),
        LeaderboardEntry(13, "u31", "TitansFan", "gt", 600, FanLevel.FAN, 12, 100, 4),
        LeaderboardEntry(14, "u32", "LucknowLion", "lsg", 400, FanLevel.FAN, 10, 80, 3),
        LeaderboardEntry(15, "u33", "NewCricFan", "mi", 150, FanLevel.NEWCOMER, 5, 30, 1),
    )

    fun getMatchLeaderboard(matchId: String): List<LeaderboardEntry> {
        return listOf(
            LeaderboardEntry(1, "u20", "CricketKing99", "csk", 320, FanLevel.ULTRA_FAN, 1, 45, 3),
            LeaderboardEntry(2, "current_user", "CricketFan42", "mi", 280, FanLevel.SUPER_FAN, 1, 38, 2, isCurrentUser = true),
            LeaderboardEntry(3, "u22", "SixerQueen", "rcb", 250, FanLevel.SUPER_FAN, 1, 32, 2),
            LeaderboardEntry(4, "u21", "IPLManiac", "mi", 220, FanLevel.ULTRA_FAN, 1, 28, 1),
            LeaderboardEntry(5, "u24", "WhistlePodu", "csk", 180, FanLevel.SUPER_FAN, 1, 22, 1),
        )
    }

    // ==================== PREDICTIONS ====================
    fun getPredictionQuestions(matchId: String): List<PredictionQuestion> {
        val match = MockCricketData.liveMatches.find { it.id == matchId }
            ?: return emptyList()

        return listOf(
            PredictionQuestion(
                id = "pq1_$matchId",
                matchId = matchId,
                type = PredictionType.MATCH_WINNER,
                question = "Who will win this match?",
                options = listOf(match.team1.shortName, match.team2.shortName),
                totalPredictions = 856,
                predictionBreakdown = mapOf(
                    match.team1.shortName to 489,
                    match.team2.shortName to 367
                )
            ),
            PredictionQuestion(
                id = "pq2_$matchId",
                matchId = matchId,
                type = PredictionType.TOP_SCORER,
                question = "Who will be the top scorer?",
                options = if (matchId == "m1") listOf("Rohit Sharma", "Suryakumar Yadav", "Ruturaj Gaikwad", "Ravindra Jadeja")
                else listOf("Virat Kohli", "Glenn Maxwell", "Andre Russell", "Sunil Narine"),
                totalPredictions = 642,
                predictionBreakdown = if (matchId == "m1") mapOf(
                    "Rohit Sharma" to 245, "Suryakumar Yadav" to 180,
                    "Ruturaj Gaikwad" to 130, "Ravindra Jadeja" to 87
                ) else mapOf(
                    "Virat Kohli" to 280, "Glenn Maxwell" to 150,
                    "Andre Russell" to 140, "Sunil Narine" to 72
                )
            ),
            PredictionQuestion(
                id = "pq3_$matchId",
                matchId = matchId,
                type = PredictionType.TOTAL_RUNS,
                question = "What will be the combined total runs?",
                options = listOf("Under 300", "300-350", "350-400", "Above 400"),
                totalPredictions = 534,
                predictionBreakdown = mapOf(
                    "Under 300" to 67, "300-350" to 198,
                    "350-400" to 185, "Above 400" to 84
                )
            ),
            PredictionQuestion(
                id = "pq4_$matchId",
                matchId = matchId,
                type = PredictionType.FIRST_WICKET_OVER,
                question = "In which over will the first wicket fall?",
                options = listOf("1-3 overs", "4-6 overs", "7-10 overs", "After 10 overs"),
                totalPredictions = 412,
                predictionBreakdown = mapOf(
                    "1-3 overs" to 98, "4-6 overs" to 156,
                    "7-10 overs" to 112, "After 10 overs" to 46
                )
            )
        )
    }

    // ==================== HEAD TO HEAD ====================
    fun getHeadToHead(team1Id: String, team2Id: String): HeadToHead {
        // Generate realistic H2H data for any team pairing
        val key = listOf(team1Id, team2Id).sorted().joinToString("_")
        return h2hData[key] ?: generateH2H(team1Id, team2Id)
    }

    private val h2hData = mapOf(
        "csk_mi" to HeadToHead(
            team1Id = "mi", team2Id = "csk",
            totalMatches = 37, team1Wins = 20, team2Wins = 16, noResults = 1,
            team1AvgScore = 172, team2AvgScore = 168,
            team1HighestScore = 218, team2HighestScore = 210,
            team1BiggestWin = "MI won by 8 wickets (2024)",
            team2BiggestWin = "CSK won by 7 wickets (2023)",
            lastFiveResults = listOf(
                H2HResult("h1", "mi", "MI won by 5 wickets", "2026-03-28"),
                H2HResult("h2", "csk", "CSK won by 15 runs", "2025-10-12"),
                H2HResult("h3", "mi", "MI won by 8 wickets", "2025-04-14"),
                H2HResult("h4", "mi", "MI won by 4 runs", "2024-11-02"),
                H2HResult("h5", "csk", "CSK won by 7 wickets", "2024-05-12"),
            ),
            topPerformers = listOf(
                H2HTopPerformer("Rohit Sharma", "mi", "894 runs in 37 matches"),
                H2HTopPerformer("MS Dhoni", "csk", "523 runs in 35 matches"),
                H2HTopPerformer("Jasprit Bumrah", "mi", "28 wickets in 30 matches"),
                H2HTopPerformer("Ravindra Jadeja", "csk", "22 wickets in 32 matches"),
            )
        ),
        "kkr_rcb" to HeadToHead(
            team1Id = "rcb", team2Id = "kkr",
            totalMatches = 32, team1Wins = 14, team2Wins = 17, noResults = 1,
            team1AvgScore = 168, team2AvgScore = 165,
            team1HighestScore = 235, team2HighestScore = 222,
            team1BiggestWin = "RCB won by 10 wickets (2023)",
            team2BiggestWin = "KKR won by 86 runs (2024)",
            lastFiveResults = listOf(
                H2HResult("h6", "kkr", "KKR won by 6 wickets", "2026-03-30"),
                H2HResult("h7", "rcb", "RCB won by 8 runs", "2025-10-05"),
                H2HResult("h8", "kkr", "KKR won by 86 runs", "2025-04-20"),
                H2HResult("h9", "rcb", "RCB won by 10 wickets", "2024-10-15"),
                H2HResult("h10", "kkr", "KKR won by 21 runs", "2024-04-18"),
            ),
            topPerformers = listOf(
                H2HTopPerformer("Virat Kohli", "rcb", "782 runs in 30 matches"),
                H2HTopPerformer("Andre Russell", "kkr", "456 runs, 18 wickets"),
                H2HTopPerformer("Glenn Maxwell", "rcb", "380 runs in 12 matches"),
                H2HTopPerformer("Sunil Narine", "kkr", "24 wickets in 28 matches"),
            )
        )
    )

    private fun generateH2H(team1Id: String, team2Id: String): HeadToHead {
        val t1 = MockCricketData.teamById(team1Id)
        val t2 = MockCricketData.teamById(team2Id)
        val total = (20..35).random()
        val t1Wins = (total * 0.4).toInt() + (-2..2).random()
        val t2Wins = total - t1Wins - 1
        return HeadToHead(
            team1Id = team1Id, team2Id = team2Id,
            totalMatches = total, team1Wins = t1Wins, team2Wins = t2Wins, noResults = 1,
            team1AvgScore = (155..175).random(), team2AvgScore = (155..175).random(),
            team1HighestScore = (195..230).random(), team2HighestScore = (195..230).random(),
            team1BiggestWin = "${t1.shortName} won by ${(5..9).random()} wickets",
            team2BiggestWin = "${t2.shortName} won by ${(20..60).random()} runs",
            lastFiveResults = listOf(
                H2HResult("g1", team1Id, "${t1.shortName} won by ${(3..7).random()} wickets", "2026-03-20"),
                H2HResult("g2", team2Id, "${t2.shortName} won by ${(10..30).random()} runs", "2025-10-10"),
                H2HResult("g3", team1Id, "${t1.shortName} won by ${(4..8).random()} wickets", "2025-04-15"),
                H2HResult("g4", team2Id, "${t2.shortName} won by ${(2..6).random()} wickets", "2024-10-08"),
                H2HResult("g5", team1Id, "${t1.shortName} won by ${(5..25).random()} runs", "2024-04-12"),
            ),
            topPerformers = emptyList()
        )
    }
}
