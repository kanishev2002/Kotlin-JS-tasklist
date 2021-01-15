package components

import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.div
import react.dom.input

external interface InputFieldProps : RProps {
    var id: String
    var placeholder: String
    var onChange: (String) -> Unit
}

external interface InputFieldState : RState {
    var value: String?
}

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