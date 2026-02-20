package model

import kotlinx.serialization.Serializable

enum class Priority {
    Low, Medium, High, Vital
}

@Serializable       //this annotation makes instances to be converted to and from Json, therefore they can be transferred over the network.
data class Task (
    val name: String,
    val description: String,
    val priority: Priority
)

