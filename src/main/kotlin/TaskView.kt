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
            div {
                val classNames = if (props.task.isCompleted) {
                    "fas fa-circle"
                } else {
                    "far fa-circle"
                }
                button {
                    attrs {
                        onClickFunction = {
                            handleClick(props.task.id)
                        }
                    }
                    i(classes = classNames) {}
                }
                div {
                    div {
                        +props.task.name
                    }
                    div {
                        +props.task.description
                    }
                }
            }
            hr {}
        }
    }
}