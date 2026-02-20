package com.example.com

import io.ktor.http.ContentType
import io.ktor.server.application.*
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages){
        exception<IllegalStateException> {call, cause ->
            call.respondText("App in illegal state as ${cause.message}")
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/test1"){
            val text = "<h1>Welcome to Yong-shopping</h1>"
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }
        get("/error-test"){
            throw IllegalStateException("Too Busy")
        }
        staticResources("/content", "mycontent")
    }
}
