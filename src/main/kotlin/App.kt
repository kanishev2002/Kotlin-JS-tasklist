import react.*
import react.dom.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event

external interface AppState : RState {
    var currentId: Int
    var inputNameValue: String
    var inputDescriptionValue: String
    var tasks: List<Task>
}

class App : RComponent<RProps, AppState>() {
    private fun handleSubmitButton() {
        if (!state.inputNameValue.isNullOrEmpty()) {
            setState {
                tasks += listOf(
                    Task(
                        currentId + 1,
                        inputNameValue,
                        inputDescriptionValue,
                        false
                    )
                )
                currentId += 1
                inputNameValue = ""
                inputDescriptionValue = ""
            }
        }
    }

    private fun handleNameInput(event: Event) {
        val value = event.currentTarget.toString()
        println(value)
        setState {
            inputNameValue = value
        }
    }

    private fun handleDescriptionInput(event: Event) {
        val value = event.currentTarget.toString()
        println(value)
        setState {
            inputDescriptionValue = value
        }
    }

    override fun RBuilder.render() {
        div(classes = "appHeader") {
            h1(classes = "headerText") {
                +"TODO List"
            }
        }
        div(classes = "app") {
            div(classes = "taskList") {
                // TODO: actually implement tasks
                // val tasks = state.tasks
                child(TaskList::class) {
                    attrs.tasks = listOf(
                        Task(1, "task1", "task description", false),
                        Task(2, "task2", "another task description", true)
                    )
                }
            }
            div(classes = "inputFields") {
                child(InputField::class) {
                    attrs {
                        value = state.inputNameValue
                        onChange = { handleNameInput(it) }
                        placeholder = "Input name of the task"
                    }
                }
                child(InputField::class) {
                    attrs {
                        value = state.inputDescriptionValue
                        onChange = { handleDescriptionInput(it) }
                        placeholder = "Input task description"
                    }
                }
                div("submit-button-area") {
                    button(classes = "submit-button") {
                        attrs {
                            onClickFunction = {
                                handleSubmitButton()
                            }
                        }
                        +"Submit"
                    }
                }
            }
        }
    }
}