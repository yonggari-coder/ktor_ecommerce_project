package com.example

import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import model.Priority
import model.Task
import java.time.Duration
import kotlin.time.Duration.Companion.seconds

fun Application.configureSockets() {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        webSocket("/tasks") {
            val tasks = listOf(
                Task("coding", "try to write clear code", Priority.Low),
                Task("debugging", "try to catch the bug", Priority.Medium),
                Task("reviewing", "review my code", Priority.High),
                Task("shopping", "Buy new items", Priority.Low)
            )

            for (task in tasks) {
                sendSerialized(task)
                delay(1000)
            }

            close(CloseReason(CloseReason.Codes.NORMAL, "ALL done"))
        }
    }
}
