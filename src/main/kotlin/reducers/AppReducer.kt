package reducers

import actions.AddTask
import actions.ChangeInputParameters
import actions.UpdateTaskCompletion
import entities.Store
import redux.RAction

fun appReducer(state: Store, action: RAction): Store = when (action) {
    is AddTask -> Store(state.tasks + action.task, mutableMapOf())
    is ChangeInputParameters -> {
        val newParameters = state.inputParameters.toMutableMap()
        newParameters[action.key] = action.value
        state.copy(inputParameters = newParameters)
    }
    is UpdateTaskCompletion -> state.copy(
        tasks = state.tasks.map {
            if (it.id == action.id) it.copy(isCompleted = !it.isCompleted) else it
        }
    )
    else -> state
}