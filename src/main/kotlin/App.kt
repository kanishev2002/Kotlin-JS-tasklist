import react.*
import react.dom.*
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import styled.*
import styles.AppStyle

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
                        this.currentId + 1,
                        this.inputNameValue,
                        this.inputDescriptionValue,
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
        styledDiv {
            css {
                +AppStyle.appHeader
            }
            styledH1 {
                css {
                    +AppStyle.headerText
                }
                +"TODO List"
            }
        }
        styledDiv {
            css {
                AppStyle.app
            }
            styledDiv {
                // TODO: actually implement tasks
                // val tasks = state.tasks
                child(TaskList::class) {
                    attrs.tasks = listOf(
                        Task(1, "task1", "task description", false)
                    )
                }
            }
            styledDiv {
                css {
                    AppStyle.inputFields
                }
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
                styledDiv {
                    styledButton {
                        css {
                            AppStyle.submitButton
                        }
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