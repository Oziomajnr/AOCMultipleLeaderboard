package com.ozioma.aocleaderboard.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object AdventOfCodeApi {

    suspend fun getAocStats(year: String, cookie: String, leaderboardCode: String): HttpResponse {
        return httpClient.post("https://adventofcode.com/$year/leaderboard/private/view/$leaderboardCode.json") {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
            headers {
                append(
                    HttpHeaders.Cookie,
                    cookie
                )
            }
        }
    }
}

val httpClient = createClient()

private fun createClient(): HttpClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

    }
    return client
}