import kotlin.js.Date

data class Task(
    val id: Int,
    val name: String,
    val description: String,
    val deadline: String,
    var isCompleted: Boolean
)

// TODO: add deadlines and date of adding