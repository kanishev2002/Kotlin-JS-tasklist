package components

import containers.ConnectedTaskView
import entities.Task
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

external interface TaskListProps : RProps {
    var tasks: List<Task>
}

class TaskList : RComponent<TaskListProps, RState>() {
    override fun RBuilder.render() {
        for (task in props.tasks) {
            ConnectedTaskView {
                key = task.id.toString()
                attrs.task = task
            }
        }
    }
}