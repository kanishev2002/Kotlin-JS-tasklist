import react.*
import react.dom.*
import kotlinx.css.*
import styled.*

external interface TaskListProps : RProps {
    var tasks: List<Task>
}

class TaskList : RComponent<TaskListProps, RState>() {
    override fun RBuilder.render() {
        for (task in props.tasks) {
            child(TaskView::class) {
                attrs.task = task
            }
        }
    }
}