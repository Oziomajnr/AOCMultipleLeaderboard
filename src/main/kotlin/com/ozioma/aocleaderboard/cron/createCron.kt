package com.ozioma.aocleaderboard.cron

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

fun createCron(scope: CoroutineScope, initialDelay: Long, delay: Long, block: suspend () -> (Unit)) {
    scope.launch {
        delay(initialDelay)
        while (true) {
            delay(delay)
            // Populate data here.
            block()
        }
    }
}