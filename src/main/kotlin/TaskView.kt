import kotlinx.html.js.onClickFunction
import react.*
import react.dom.*
import styled.*
import styles.TaskViewStyle

external interface TaskViewProps : RProps {
    var task: Task
}

class TaskView : RComponent<TaskViewProps, RState>() {
    private fun handleClick(id: Int) {
        TODO()
    }

    override fun RBuilder.render() {
        styledDiv {
            styledDiv {
                css {
                    +TaskViewStyle.taskView
                }
                val classNames = if (props.task.isCompleted) {
                    "fas fa-circle"
                } else {
                    "far fa-circle"
                }
                styledButton {
                    css {
                        +TaskViewStyle.taskCompletionButton
                    }
                    attrs {
                        onClickFunction = {
                            handleClick(props.task.id)
                        }
                    }
                    styledI {
                        css {
                            classes = mutableListOf(classNames)
                            +(if (props.task.isCompleted) {
                                TaskViewStyle.completedTask
                            } else {
                                TaskViewStyle.incompleteTask
                            })
                        }
                    }
                }
                styledDiv {
                    css {
                        +TaskViewStyle.task
                    }
                    styledDiv {
                        css {
                            +TaskViewStyle.taskName
                        }
                        +props.task.name
                    }
                    styledDiv {
                        css {
                            +TaskViewStyle.taskDescription
                        }
                        +props.task.description
                    }
                }
            }
            styledHr {
                css {
                    +TaskViewStyle.hr
                }
            }
        }
    }
}