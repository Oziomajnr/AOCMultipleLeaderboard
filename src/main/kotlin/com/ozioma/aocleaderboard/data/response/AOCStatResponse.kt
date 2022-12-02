package com.ozioma.aocleaderboard.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AOCStatResponse(
    @SerialName("owner_id")
    val ownerID: Long,
    @SerialName("event")
    val event: String,
    @SerialName("members")
    val members: Map<String, Member>
)

@Serializable
data class Member(
    val id: Long,
    val name: String? = null,

    @SerialName("last_star_ts")
    val lastStarTs: Long,

    @SerialName("stars")
    val stars: Long,

    @SerialName("local_score")
    val localScore: Long,

    @SerialName("completion_day_level")
    val completionDayLevel: Map<String, Map<String, StarStat>>,

    @SerialName("global_score")
    val globalScore: Long


) {
    override fun toString(): String {
        return "Name: $name"
    }
}

@Serializable
data class StarStat(
    @SerialName("get_star_ts") val timeStarGotten: Long,
    @SerialName("star_index") val starIndex: Long
)