package containers

import actions.UpdateTaskCompletion
import components.TaskView
import components.TaskViewProps
import entities.Store
import entities.Task
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.WrapperAction

external interface TaskViewStateProps : RProps {
    var task: Task
}

external interface TaskViewDispatchProps : RProps {
    var handleClick: () -> Unit
}

val ConnectedTaskView: RClass<TaskViewStateProps> =
    rConnect<Store, UpdateTaskCompletion, WrapperAction, TaskViewStateProps,
            TaskViewStateProps, TaskViewDispatchProps, TaskViewProps>(
        { _, ownProps ->
            task = ownProps.task
        },
        { dispatch, ownProps ->
            handleClick = { dispatch(UpdateTaskCompletion(ownProps.task.id)) }
        }
    )(TaskView::class.js.unsafeCast<RClass<TaskViewProps>>())