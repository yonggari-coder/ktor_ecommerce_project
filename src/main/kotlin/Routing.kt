package com.example.com

import com.example.com.model.Product
import com.example.com.model.ProductRepository
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerializationException

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
        route("/product"){
            get{
                val products = ProductRepository.allProducts()
                call.respond(products)
            }

            post {
                try {
                    val product = call.receive<Product>()
                    ProductRepository.addProduct(product)
                    call.respond(HttpStatusCode.Created)
                } catch (ex: IllegalStateException) {
                    call.respond(HttpStatusCode.BadRequest)
                } catch (ex: SerializationException) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{productName}") {
                val name = call.parameters["productName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }

                if (ProductRepository.removeProduct(name)){
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            get("/byName/{productName}") {
                val name = call.parameters["productName"]
                if (name == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                val product = ProductRepository.productByName(name)
                if (product == null) {
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(product)
            }
        }

        staticResources("/content", "mycontent")
    }
}
