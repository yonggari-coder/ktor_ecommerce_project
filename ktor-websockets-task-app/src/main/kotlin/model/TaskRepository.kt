package com.example.model

import model.Priority
import model.Task

object TaskRepository {
    private val tasks = mutableListOf(
        Task("coding", "try to write clear code", Priority.Low),
        Task("debugging", "try to catch the bug", Priority.Medium),
        Task("reviewing", "review my code", Priority.High),
        Task("shopping", "Buy new items", Priority.Low)
    )

    fun allTasks(): List<Task> = tasks

    fun tasksByPriority(priority: Priority) = tasks.filter {
        it.priority == priority
    }

    fun taskByName(name: String) = tasks.find {
        it.name.equals(name, ignoreCase = true)
    }

    fun addTask(task: Task) {
        if(taskByName(task.name) != null) {
            throw IllegalStateException("Cannot duplicate task names!")
        }
        tasks.add(task)
    }

    fun removeTask(name: String): Boolean {
        return tasks.removeIf { it.name == name}
    }
}