package components

import entities.Task
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.div
import react.dom.hr
import react.dom.i

external interface TaskViewProps : RProps {
    var handleClick: () -> Unit
    var task: Task
}

class TaskView : RComponent<TaskViewProps, RState>() {

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
                            props.handleClick()
                        }
                    }
                    i(classes = classNames) {}
                }
                div(classes = "task") {
                    div(classes = "taskName") {
                        +props.task.name
                    }
                    if(props.task.description.isNotEmpty()) {
                        div(classes = "taskDescription") {
                            +props.task.description
                        }
                    }
                    if(props.task.deadline.isNotEmpty()) {
                        div(classes = "taskDescription"){
                            +("Due on: " + props.task.deadline)
                        }
                    }
                }
            }
            hr(classes = "hr") {}
        }
    }
}