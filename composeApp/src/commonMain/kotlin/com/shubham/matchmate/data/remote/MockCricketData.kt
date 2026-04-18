package com.shubham.matchmate.data.remote

import com.shubham.matchmate.data.model.*

object MockCricketData {

    val teams = listOf(
        Team("mi", "Mumbai Indians", "MI", primaryColor = 0xFF004BA0),
        Team("csk", "Chennai Super Kings", "CSK", primaryColor = 0xFFFFC107),
        Team("rcb", "Royal Challengers Bengaluru", "RCB", primaryColor = 0xFFD4213D),
        Team("kkr", "Kolkata Knight Riders", "KKR", primaryColor = 0xFF3A225D),
        Team("dc", "Delhi Capitals", "DC", primaryColor = 0xFF004C93),
        Team("srh", "Sunrisers Hyderabad", "SRH", primaryColor = 0xFFFF822A),
        Team("pbks", "Punjab Kings", "PBKS", primaryColor = 0xFFED1B24),
        Team("rr", "Rajasthan Royals", "RR", primaryColor = 0xFFEA1A85),
        Team("gt", "Gujarat Titans", "GT", primaryColor = 0xFF1C1C2B),
        Team("lsg", "Lucknow Super Giants", "LSG", primaryColor = 0xFF00A3E0),
    )

    fun teamById(id: String): Team = teams.first { it.id == id }

    val venues = mapOf(
        "mi" to "Wankhede Stadium, Mumbai",
        "csk" to "MA Chidambaram Stadium, Chennai",
        "rcb" to "M. Chinnaswamy Stadium, Bengaluru",
        "kkr" to "Eden Gardens, Kolkata",
        "dc" to "Arun Jaitley Stadium, Delhi",
        "srh" to "Rajiv Gandhi Intl. Stadium, Hyderabad",
        "pbks" to "IS Bindra Stadium, Mohali",
        "rr" to "Sawai Mansingh Stadium, Jaipur",
        "gt" to "Narendra Modi Stadium, Ahmedabad",
        "lsg" to "BRSABV Ekana Stadium, Lucknow",
    )

    val liveMatches = listOf(
        Match(
            id = "m1",
            team1 = teamById("mi"),
            team2 = teamById("csk"),
            venue = "Wankhede Stadium, Mumbai",
            startTime = "2026-04-18T19:30:00+05:30",
            status = MatchStatus.LIVE,
            scoreTeam1 = InningsScore(167, 4, 16.3f),
            scoreTeam2 = null,
            currentBatsmen = listOf(
                BatsmanScore("Rohit Sharma", 72, 48, 6, 4, true),
                BatsmanScore("Suryakumar Yadav", 41, 28, 3, 2, false)
            ),
            currentBowler = BowlerScore("Ravindra Jadeja", 3.3f, 0, 28, 1),
            tossWinner = "MI",
            tossDecision = "bat"
        ),
        Match(
            id = "m2",
            team1 = teamById("rcb"),
            team2 = teamById("kkr"),
            venue = "M. Chinnaswamy Stadium, Bengaluru",
            startTime = "2026-04-18T15:30:00+05:30",
            status = MatchStatus.LIVE,
            scoreTeam1 = InningsScore(198, 5, 20.0f),
            scoreTeam2 = InningsScore(142, 3, 15.2f),
            currentBatsmen = listOf(
                BatsmanScore("Andre Russell", 38, 18, 2, 4, true),
                BatsmanScore("Nitish Rana", 22, 19, 3, 0, false)
            ),
            currentBowler = BowlerScore("Mohammed Siraj", 3.2f, 0, 32, 1),
            tossWinner = "RCB",
            tossDecision = "bat"
        ),
    )

    val completedMatches = listOf(
        Match(
            id = "m5",
            team1 = teamById("gt"),
            team2 = teamById("lsg"),
            venue = "Narendra Modi Stadium, Ahmedabad",
            startTime = "2026-04-17T19:30:00+05:30",
            status = MatchStatus.COMPLETED,
            scoreTeam1 = InningsScore(185, 6, 20.0f),
            scoreTeam2 = InningsScore(186, 4, 19.2f),
            result = "LSG won by 6 wickets",
            tossWinner = "GT",
            tossDecision = "bat"
        ),
        Match(
            id = "m6",
            team1 = teamById("csk"),
            team2 = teamById("rcb"),
            venue = "MA Chidambaram Stadium, Chennai",
            startTime = "2026-04-16T19:30:00+05:30",
            status = MatchStatus.COMPLETED,
            scoreTeam1 = InningsScore(210, 4, 20.0f),
            scoreTeam2 = InningsScore(195, 8, 20.0f),
            result = "CSK won by 15 runs",
            tossWinner = "CSK",
            tossDecision = "bat"
        ),
    )

    /**
     * Generate upcoming matches ensuring the user's favorite team appears
     * in at least 3 out of 5 matches (like a real IPL schedule).
     */
    fun generateUpcomingMatches(favoriteTeamId: String): List<Match> {
        val favTeam = teams.find { it.id == favoriteTeamId } ?: teamById("mi")
        val otherTeams = teams.filter { it.id != favoriteTeamId }.shuffled()

        val matches = mutableListOf<Match>()

        // Match 1: Favorite team vs opponent — tomorrow evening
        matches.add(
            Match(
                id = "u1",
                team1 = favTeam,
                team2 = otherTeams[0],
                venue = venues[favoriteTeamId] ?: "Wankhede Stadium, Mumbai",
                startTime = "2026-04-19T19:30:00+05:30",
                status = MatchStatus.UPCOMING
            )
        )

        // Match 2: Neutral match — tomorrow afternoon
        matches.add(
            Match(
                id = "u2",
                team1 = otherTeams[1],
                team2 = otherTeams[2],
                venue = venues[otherTeams[1].id] ?: "Eden Gardens, Kolkata",
                startTime = "2026-04-19T15:30:00+05:30",
                status = MatchStatus.UPCOMING
            )
        )

        // Match 3: Favorite team AWAY — day after tomorrow
        matches.add(
            Match(
                id = "u3",
                team1 = otherTeams[3],
                team2 = favTeam,
                venue = venues[otherTeams[3].id] ?: "Arun Jaitley Stadium, Delhi",
                startTime = "2026-04-20T19:30:00+05:30",
                status = MatchStatus.UPCOMING
            )
        )

        // Match 4: Neutral match
        matches.add(
            Match(
                id = "u4",
                team1 = otherTeams[4],
                team2 = otherTeams[5],
                venue = venues[otherTeams[4].id] ?: "IS Bindra Stadium, Mohali",
                startTime = "2026-04-21T15:30:00+05:30",
                status = MatchStatus.UPCOMING
            )
        )

        // Match 5: Favorite team HOME — big weekend match
        matches.add(
            Match(
                id = "u5",
                team1 = favTeam,
                team2 = otherTeams[6],
                venue = venues[favoriteTeamId] ?: "Wankhede Stadium, Mumbai",
                startTime = "2026-04-22T19:30:00+05:30",
                status = MatchStatus.UPCOMING
            )
        )

        // Match 6: Neutral double-header
        matches.add(
            Match(
                id = "u6",
                team1 = otherTeams[7],
                team2 = otherTeams[8],
                venue = venues[otherTeams[7].id] ?: "Narendra Modi Stadium, Ahmedabad",
                startTime = "2026-04-22T15:30:00+05:30",
                status = MatchStatus.UPCOMING
            )
        )

        // Match 7: Favorite team — midweek
        matches.add(
            Match(
                id = "u7",
                team1 = otherTeams[0],
                team2 = favTeam,
                venue = venues[otherTeams[0].id] ?: "Eden Gardens, Kolkata",
                startTime = "2026-04-23T19:30:00+05:30",
                status = MatchStatus.UPCOMING
            )
        )

        return matches
    }

    val chatMessages = listOf(
        ChatMessage("c1", "m1", "u1", "CricketFan42", userTeamId = "mi", message = "What a shot by Rohit! \uD83D\uDD25", timestamp = "2026-04-18T20:15:00+05:30"),
        ChatMessage("c2", "m1", "u2", "ChennaiExpress", userTeamId = "csk", message = "Jadeja will turn it around in death overs \uD83D\uDCAA", timestamp = "2026-04-18T20:15:30+05:30"),
        ChatMessage("c3", "m1", "u3", "MumbaiKar", userTeamId = "mi", message = "SKY is in beast mode today!", timestamp = "2026-04-18T20:16:00+05:30"),
        ChatMessage("c4", "m1", "u4", "IPLJunkie", userTeamId = "csk", message = "Need wickets now, this partnership is deadly", timestamp = "2026-04-18T20:16:30+05:30"),
        ChatMessage("c5", "m1", "u5", "BlueFan", userTeamId = "mi", message = "200+ loading... MI all the way! \uD83C\uDFCF", timestamp = "2026-04-18T20:17:00+05:30"),
        ChatMessage("c6", "m2", "u6", "RCBian", userTeamId = "rcb", message = "198 is a great total! Ee Sala Cup Namde!", timestamp = "2026-04-18T16:00:00+05:30"),
        ChatMessage("c7", "m2", "u7", "KKRFan", userTeamId = "kkr", message = "Russell can chase anything, stay calm \uD83D\uDE0E", timestamp = "2026-04-18T16:30:00+05:30"),
    )

    val polls = listOf(
        Poll(
            id = "p1",
            matchId = "m1",
            question = "Who will be Player of the Match?",
            options = listOf(
                PollOption("o1", "Rohit Sharma", 42),
                PollOption("o2", "Ravindra Jadeja", 28),
                PollOption("o3", "Jasprit Bumrah", 18),
                PollOption("o4", "Suryakumar Yadav", 35),
            ),
            createdBy = "system",
            totalVotes = 123
        ),
        Poll(
            id = "p2",
            matchId = "m1",
            question = "Will MI cross 200?",
            options = listOf(
                PollOption("o5", "Yes, easily!", 67),
                PollOption("o6", "Just about", 34),
                PollOption("o7", "No way", 22),
            ),
            createdBy = "system",
            totalVotes = 123
        ),
        Poll(
            id = "p3",
            matchId = "m2",
            question = "Can KKR chase 199?",
            options = listOf(
                PollOption("o8", "Russell will do it!", 55),
                PollOption("o9", "Too much to chase", 40),
            ),
            createdBy = "system",
            totalVotes = 95
        ),
    )

    val threads = listOf(
        PostMatchThread(
            id = "t1",
            matchId = "m5",
            title = "Post-Match: GT vs LSG \u2014 LSG chase it down!",
            body = "What a chase by Lucknow! KL Rahul was magnificent with his 89*(52). Gujarat's bowlers had no answers in the death overs. Thoughts?",
            authorId = "u8",
            authorName = "CricketAnalyst",
            createdAt = "2026-04-17T23:00:00+05:30",
            commentCount = 24,
            upvotes = 56
        ),
        PostMatchThread(
            id = "t2",
            matchId = "m6",
            title = "Post-Match: CSK vs RCB \u2014 Dhoni masterclass!",
            body = "CSK defended 210 comfortably. RCB's middle order collapsed yet again. Is this the same old RCB or can they bounce back?",
            authorId = "u9",
            authorName = "IPLWatcher",
            createdAt = "2026-04-16T23:30:00+05:30",
            commentCount = 45,
            upvotes = 89
        ),
    )

    val comments = listOf(
        Comment("cm1", "t1", "u10", "LSGFan", "lsg", "KL Rahul is the most underrated batsman in IPL!", "2026-04-17T23:05:00+05:30", 12),
        Comment("cm2", "t1", "u11", "GTSupporter", "gt", "We need better death bowling options. Disappointing.", "2026-04-17T23:10:00+05:30", 8),
        Comment("cm3", "t1", "u12", "NeutralFan", "", "That last over six by Rahul was incredible! Match-winning.", "2026-04-17T23:15:00+05:30", 15),
        Comment("cm4", "t2", "u13", "CSKLion", "csk", "Whistle Podu! CSK forever \uD83D\uDC9B", "2026-04-16T23:35:00+05:30", 20),
        Comment("cm5", "t2", "u14", "RCBHeart", "rcb", "Pain. Just pain. Every year same story \uD83D\uDE22", "2026-04-16T23:40:00+05:30", 18),
    )

    val badges = listOf(
        Badge("b1", "Super Fan", "star", "Followed 50+ matches"),
        Badge("b2", "Chat Champion", "chat", "Sent 100+ messages in match chats"),
        Badge("b3", "Poll Master", "poll", "Voted in 25+ polls"),
        Badge("b4", "Early Bird", "clock", "Joined within first week of the season"),
        Badge("b5", "Team Loyalist", "shield", "Never changed your favorite team"),
    )

    val currentUser = FanProfile(
        uid = "current_user",
        displayName = "CricketFan42",
        favoriteTeamId = "mi",
        badges = listOf(badges[0], badges[3], badges[4]),
        matchesFollowed = 28,
        messagesSent = 156,
        pollsVoted = 34,
        joinedAt = "2026-03-20T10:00:00+05:30"
    )
}
