package components

import containers.connectedInputField
import entities.Task
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.div
import react.dom.h1


external interface AppProps : RProps {
    var tasks: List<Task>
    var inputParameters: MutableMap<String, String>
    var onClick: (String?, String?, String?) -> Unit
}


class App : RComponent<AppProps, RState>() {
    companion object Strings {
        const val nameId = "Name input field"
        const val namePlaceholder = "Input name of the task"

        const val descriptionId = "Description input field"
        const val descriptionPlaceholder = "Input task description"

        const val deadlineId = "Deadline input field"
        const val deadlinePlaceholder = "Input task deadline"
    }

    override fun RBuilder.render() {
        div(classes = "appHeader") {
            h1(classes = "headerText") {
                +"TODO List"
            }
        }
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
                    }
                }
                connectedInputField {
                    attrs {
                        id = descriptionId
                        placeholder = descriptionPlaceholder
                    }
                }
                connectedInputField {
                    attrs {
                        id = deadlineId
                        placeholder = deadlinePlaceholder
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