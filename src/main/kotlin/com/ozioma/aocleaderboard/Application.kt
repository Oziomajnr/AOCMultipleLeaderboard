package com.ozioma.aocleaderboard

import com.ozioma.aocleaderboard.plugins.configureRouting
import com.ozioma.aocleaderboard.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main(args: Array<String>) {
    EngineMain.main(args)
}


fun Application.module() {
    configureSerialization()
    configureRouting()
}
