package com.ozioma.aocleaderboard.repository

import com.ozioma.aocleaderboard.data.config.LeaderboardConfig

object ConfigRepository {
    fun getLeaderboardConfigs(): List<LeaderboardConfig> {
        return listOf(
            LeaderboardConfig(
                "your cookie",
                "your leaderboard code"
            ),
            LeaderboardConfig("your cookie",
                "your leaderboard code")
        )
    }
}