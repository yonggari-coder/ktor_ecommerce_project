package com.example.model

class FakeTaskRepository : TaskRepository {
    private val tasks = mutableListOf(
        Task("coding", "try to write clear code", Priority.Low),
        Task("refactoring", "make code human readable", Priority.Medium),
        Task("debugging", "Catch the bug", Priority.High),
        Task("deploying", "Deploy my own project", Priority.High)
    )

    override fun allTasks(): List<Task> = tasks

    override fun tasksByPriority(priority: Priority) = tasks.filter{
        it.priority == priority
    }

    override fun taskByName(name: String) = tasks.find {
        it.name.equals(name, ignoreCase = true)
    }

    override fun addTask(task: Task) {
        if (taskByName(task.name) != null) {
            throw IllegalStateException("Cannot duplciate task names!")
        }
        tasks.add(task)
    }

    override fun removeTask(name: String): Boolean {
        return tasks.removeIf { it.name == name }
    }
}