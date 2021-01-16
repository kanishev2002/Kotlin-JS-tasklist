package containers

import actions.ChangeInputParameters
import components.InputField
import components.InputFieldProps
import entities.Store
import kotlinx.html.InputType
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import redux.WrapperAction

external interface InputStateProps : RProps {
    var id: String
    var placeholder: String
    var type: InputType
}

external interface InputDispatchProps : RProps {
    var onChange: (String) -> Unit
}

val connectedInputField: RClass<InputStateProps> =
    rConnect<Store, ChangeInputParameters, WrapperAction, InputStateProps,
            InputStateProps, InputDispatchProps, InputFieldProps>(
        { _, ownProps ->
            id = ownProps.id
            placeholder = ownProps.placeholder
            type = ownProps.type
        },
        { dispatch, ownProps ->
            onChange = {
                dispatch(ChangeInputParameters(ownProps.id, it))
            }
        }
    )(InputField::class.js.unsafeCast<RClass<InputFieldProps>>())