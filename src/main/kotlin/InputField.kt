import kotlinx.html.id
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*

external interface InputFieldProps : RProps {
    //var value: String
    //var onChange: (event: Event) -> Unit
    var id: String
    var placeholder: String
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
                        setState {
                            this.value = value
                        }
                    }
                }
            }
        }
    }
}