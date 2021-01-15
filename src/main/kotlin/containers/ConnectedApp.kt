package containers

import actions.AddTask
import components.App
import components.AppProps
import entities.Store
import entities.Task
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.WrapperAction


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