import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.div
import react.dom.h1
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction
import redux.createStore
import redux.rEnhancer

data class Store(
    var tasks: List<Task>,
    var inputParameters: MutableMap<String, String>
)

class AddTask(val task: Task) : RAction

private fun appReducer(state: Store, action: RAction): Store = when (action) {
    is AddTask -> Store(state.tasks + action.task, mutableMapOf())
    is ChangeInputParameters -> {
        val newParameters = state.inputParameters.toMutableMap()
        newParameters[action.key] = action.value
        state.copy(inputParameters = newParameters)
    }
    else -> state
}

val store = createStore(::appReducer, Store(emptyList(), mutableMapOf()), rEnhancer())

object IdGenerator {
    var id = 1
    fun get() = id++
}

private interface AppStateProps : RProps {
    var tasks: List<Task>
    var inputParameters: MutableMap<String, String>
}

private interface AppDispatchProps : RProps {
    var onClick: (String?, String?, String?) -> Unit
}

val connectedApp: RClass<RProps> =
    rConnect<Store, AddTask, WrapperAction, RProps, AppStateProps, AppDispatchProps, AppProps>(
        { state, _ ->
            tasks = state.tasks
            inputParameters = state.inputParameters
        },
        { dispatch, _ ->
            onClick = { taskName, taskDescription, taskDeadline ->
                println(taskName)
                println(taskDescription)
                println(taskDeadline)
                //val taskName = (it.target as? HTMLInputElement)?.value
                //val taskDescription = (it.target as? HTMLInputElement)?.value ?: ""
                if (!taskName.isNullOrEmpty()) {
                    dispatch(
                        AddTask(
                            Task(
                                IdGenerator.id,
                                taskName,
                                taskDescription ?: "",
                                taskDeadline ?: "",
                                false
                            )
                        )
                    )
                }
            }
        }
    )(App::class.js.unsafeCast<RClass<AppProps>>())

external interface AppProps : RProps {
    var tasks: List<Task>
    var inputParameters: MutableMap<String, String>
    var onClick: (String?, String?, String?) -> Unit
}


class App : RComponent<AppProps, RState>() {

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
                    attrs.tasks = props.tasks
                }
            }
            div(classes = "inputFields") {
                connectedInputField {
                    attrs {
                        //value = state.inputNameValue
                        //onChange = { handleNameInput(it) }
                        id = "Name input field"
                        placeholder = "Input name of the task"
                    }
                }
                connectedInputField {
                    attrs {
                        //value = state.inputDescriptionValue
                        //onChange = { handleDescriptionInput(it) }
                        id = "Description input field"
                        placeholder = "Input task description"
                    }
                }
                connectedInputField {
                    attrs {
                        id = "Deadline input field"
                        placeholder = "Input task deadline"
                    }
                }
                div("submit-button-area") {
                    button(classes = "submit-button") {
                        attrs {
                            onClickFunction = {
                                props.onClick(
                                    props.inputParameters["Name input field"],
                                    props.inputParameters["Description input field"],
                                    props.inputParameters["Deadline input field"]
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