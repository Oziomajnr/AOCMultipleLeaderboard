package com.ozioma.aocleaderboard.plugins


import com.ozioma.aocleaderboard.data.config.LeaderboardConfig
import com.ozioma.aocleaderboard.data.response.AOCStatResponse
import com.ozioma.aocleaderboard.network.AdventOfCodeApi
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll


fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        aocRouting()
    }
}

@FlowPreview
fun Route.aocRouting() {
    val port = application.environment.config.propertyOrNull("ktor.deployment.port")?.getString()
    route("/") {
        get("") {
            call.respondText("Listening on port $port")
        }


        route("/leaderboard") {
            get("/{year}") {
                val year = getYear(call)

                val leaderboardIds = application.environment.config.property("aocConfig.leaderboardIds").getList()
                val leaderboardCookies = application.environment.config.property("aocConfig.cookies").getList()
               val leaderboardConfigs = leaderboardIds.zip(leaderboardCookies).map {
                    LeaderboardConfig(it.second, it.first)
                }
                val result = leaderboardConfigs.map { leaderboardConfig ->
                    async {
                        Pair(

                            AdventOfCodeApi.getAocStats(
                                year,
                                leaderboardConfig.cookie,
                                leaderboardConfig.leaderboardCode
                            ), leaderboardConfig
                        )
                    }

                }.awaitAll().map { response ->
                    if (response.first.status != HttpStatusCode.OK) {
                        call.respond(
                            response.first.status,
                            "Failed to get leaderboard stat for code ${response.second.leaderboardCode}"
                        )
                    }
                    response.first.body() as AOCStatResponse
                }.zipWithNext { a, b ->
                    AOCStatResponse(a.ownerID, year, a.members.toMutableMap().plus(b.members))
                }.map { stateResponse ->
                    stateResponse.members.map {
                        it.value
                    }
                }.flatten().sortedWith { a, b ->
                    val comparisonByStars = a.stars.compareTo(b.stars)
                    if (comparisonByStars == 0) {
                        b.lastStarTs.compareTo(a.lastStarTs)
                    } else {
                        comparisonByStars
                    }

                }.reversed()
                call.respond(result)
            }

        }

    }

}

private fun getYear(call: ApplicationCall): String {
    return call.parameters["year"] ?: "2022"
}



