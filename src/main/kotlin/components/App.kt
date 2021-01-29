package components

import containers.connectedInputField
import entities.Task
import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div


external interface AppProps : RProps {
    var tasks: List<Task>
    var inputParameters: MutableMap<String, String>
    var onClick: (String?, String?, String?) -> Unit
}

external interface AppState : RState {
    var isLoggedIn: Boolean
}

val header = functionalComponent<RProps> {
    div(classes = "appHeader") {
        div(classes = "headerText") {
            +"TODO List"
        }
    }
}

class App : RComponent<AppProps, AppState>() {
    companion object Strings {
        const val nameId = "Name input field"
        const val namePlaceholder = "Input name of the task"

        const val descriptionId = "Description input field"
        const val descriptionPlaceholder = "Input task description"

        const val deadlineId = "Deadline input field"
        const val deadlinePlaceholder = "Input task deadline"
    }

    override fun RBuilder.render() {
        if(!state.isLoggedIn){
            child(login) {
                attrs{
                    setLoggedIn = {
                        setState {
                            isLoggedIn = it
                        }
                    }
                }
            }
        }
        else {
            child(header) {}
            div(classes = "app") {
                div(classes = "taskList") {
                    child(TaskList::class) {
                        attrs.tasks = props.tasks
                    }
                }
                div(classes = "inputFields") {
                    connectedInputField {
                        attrs {
                            id = nameId
                            placeholder = namePlaceholder
                            type = InputType.text
                        }
                    }
                    connectedInputField {
                        attrs {
                            id = descriptionId
                            placeholder = descriptionPlaceholder
                            type = InputType.text
                        }
                    }
                    connectedInputField {
                        attrs {
                            id = deadlineId
                            placeholder = deadlinePlaceholder
                            type = InputType.dateTimeLocal
                        }
                    }
                    div("submit-button-area") {
                        button(classes = "submit-button") {
                            attrs {
                                onClickFunction = {
                                    props.onClick(
                                        props.inputParameters[nameId],
                                        props.inputParameters[descriptionId],
                                        props.inputParameters[deadlineId]
                                    )

                                }
                            }
                            +"Submit"
                        }
                    }
                }
            }
        }
    }
}