import kotlinx.browser.document
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.button
import react.dom.div
import react.dom.h1
import react.redux.provider
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction
import redux.createStore
import redux.rEnhancer
import kotlin.random.Random

class AddTask(val task: Task) : RAction

private fun appReducer(state: Array<Task> = emptyArray(), action: RAction): Array<Task> = when (action) {
    is AddTask -> state + action.task
    else -> state
}

val store = createStore(::appReducer, emptyArray(), rEnhancer())

object IdGenerator {
    var id = 1
    fun get() = id++
}

private interface AppStateProps : RProps {
    var tasks: Array<Task>
}

private interface AppDispatchProps : RProps {
    var onClick: () -> Unit
}

val connectedApp: RClass<AppProps> =
    rConnect<Array<Task>, AddTask, WrapperAction, RProps, AppStateProps, AppDispatchProps, AppProps>(
        { state, _ ->
            tasks = state
        },
        { dispatch, _ ->
            onClick = {
                val taskName = (document.getElementById(
                    "Name input field"
                ) as? HTMLInputElement)?.value
                val taskDescription = (document.getElementById(
                    "Description input field"
                ) as? HTMLInputElement)?.value ?: ""
                if (!taskName.isNullOrEmpty()) {
                    dispatch(
                        AddTask(
                            Task(
                                IdGenerator.id,
                                taskName,
                                taskDescription,
                                false
                            )
                        )
                    )
                }
            }
        }
    )(App::class.js.unsafeCast<RClass<AppProps>>())

external interface AppProps : RProps {
    var tasks: Array<Task>
    var onClick: () -> Unit
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
                child(InputField::class) {
                    attrs {
                        //value = state.inputNameValue
                        //onChange = { handleNameInput(it) }
                        id = "Name input field"
                        placeholder = "Input name of the task"
                    }
                }
                child(InputField::class) {
                    attrs {
                        //value = state.inputDescriptionValue
                        //onChange = { handleDescriptionInput(it) }
                        id = "Description input field"
                        placeholder = "Input task description"
                    }
                }
                div("submit-button-area") {
                    button(classes = "submit-button") {
                        attrs {
                            onClickFunction = {
                                props.onClick()
                            }
                        }
                        +"Submit"
                    }
                }
            }
        }
    }
}