import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.redux.rConnect
import redux.RAction
import redux.WrapperAction

external interface InputStateProps : RProps {
    var id: String
    var placeholder: String
}

external interface InputDispatchProps : RProps {
    var onChange: (String) -> Unit
    //var value: String?
}

external interface InputFieldProps : RProps {
    var id: String
    var placeholder: String
    var onChange: (String) -> Unit
    //var value: String?
}

external interface InputFieldState : RState {
    var value: String?
}

class ChangeInputParameters(val key: String, val value: String) : RAction

val connectedInputField: RClass<InputStateProps> =
    rConnect<Store, ChangeInputParameters, WrapperAction, InputStateProps,
            InputStateProps, InputDispatchProps, InputFieldProps>(
        { _, ownProps ->
            id = ownProps.id
            placeholder = ownProps.placeholder
        },
        { dispatch, ownProps ->
            onChange = {
                dispatch(ChangeInputParameters(ownProps.id, it))
            }
        }
    )(InputField::class.js.unsafeCast<RClass<InputFieldProps>>())

class InputField : RComponent<InputFieldProps, InputFieldState>() {
    override fun RBuilder.render() {
        div(classes = "inputField") {
            input(classes = "inputBorder") {
                attrs {
                    id = props.id
                    value = state.value ?: ""
                    placeholder = props.placeholder
                    onChangeFunction = {
                        val value = (it.target as? HTMLInputElement)?.value
                        props.onChange(value ?: "")
                        setState {
                            this.value = value
                        }
                    }
                }
            }
        }
    }
}