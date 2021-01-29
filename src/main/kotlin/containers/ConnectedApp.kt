package containers

import actions.AddTask
import components.App
import components.AppProps
import entities.Store
import entities.Task
import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.WrapperAction
import kotlin.js.Date
import kotlin.random.Random


object IdGenerator {
    var id = 1
        get() = Random.nextInt()
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
                if (!taskName.isNullOrEmpty()) {
                    val newTask = Task(
                        IdGenerator.id,
                        taskName,
                        taskDescription ?: "",
                        taskDeadline ?: "",
                        Date(Date.now()),
                        false
                    )
                    dispatch(AddTask(newTask))
                    (document.getElementById(App.nameId) as? HTMLInputElement)?.value = ""
                    (document.getElementById(App.descriptionId) as? HTMLInputElement)?.value = ""
                    (document.getElementById(App.deadlineId) as? HTMLInputElement)?.value = ""
                }
            }
        }
    )(App::class.js.unsafeCast<RClass<AppProps>>())