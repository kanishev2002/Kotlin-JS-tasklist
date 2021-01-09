import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*

external interface TaskViewProps : RProps {
    var task: Task
}

class TaskView : RComponent<TaskViewProps, RState>() {
    private fun handleClick(id: Int) {
        TODO()
    }

    override fun RBuilder.render() {
        div {
            div(classes = "taskView") {
                val classNames = if (props.task.isCompleted) {
                    "fas fa-circle completedTask"
                } else {
                    "far fa-circle incompleteTask"
                }
                button(classes = "taskCompletionButton") {
                    attrs {
                        onClickFunction = {
                            handleClick(props.task.id)
                        }
                    }
                    i(classes = classNames) {}
                }
                div(classes = "task") {
                    div(classes = "taskName") {
                        +props.task.name
                    }
                    div(classes = "taskDescription") {
                        +props.task.description
                    }
                }
            }
            hr(classes = "hr") {}
        }
    }
}