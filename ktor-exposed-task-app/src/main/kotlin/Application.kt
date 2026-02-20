package com.example

import com.example.model.FakeTaskRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val repository = FakeTaskRepository()

    configureSerialization(repository)
    configureDatabases()
    configureRouting()
}

// this kt file shows "injecting an instance of FakeTaskRepository into configureSerialization().
// It makes easy to replace FakeTaskRepository to PostgresTaskRepository.