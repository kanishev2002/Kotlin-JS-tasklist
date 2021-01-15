package reducers

import actions.AddTask
import actions.ChangeInputParameters
import entities.Store
import redux.RAction

fun appReducer(state: Store, action: RAction): Store = when (action) {
    is AddTask -> Store(state.tasks + action.task, mutableMapOf())
    is ChangeInputParameters -> {
        val newParameters = state.inputParameters.toMutableMap()
        newParameters[action.key] = action.value
        state.copy(inputParameters = newParameters)
    }
    else -> state
}