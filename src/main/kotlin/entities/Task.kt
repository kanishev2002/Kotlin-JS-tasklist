package entities

import kotlin.js.Date

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val deadline: String,
    val timeStamp: Date,
    var isCompleted: Boolean
)

// TODO: add deadlines and date of adding