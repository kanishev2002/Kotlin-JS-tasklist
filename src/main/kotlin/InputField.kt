import kotlinx.html.js.onChangeFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*

external interface InputFieldProps : RProps {
    var value: String
    var onChange: (event: Event) -> Unit
    var placeholder: String
}


class InputField : RComponent<InputFieldProps, RState>() {
    override fun RBuilder.render() {
<<<<<<< HEAD
        styledDiv {
            css {
                +InputFieldStyle.inputField
            }
            styledInput {
                css {
                    +InputFieldStyle.inputBorder
                }
=======
        div {
            input {
>>>>>>> parent of 7a8cd8e... Added styling
                attrs {
                    value = props.value
                    placeholder = props.placeholder
                    onChangeFunction = props.onChange
                }
            }
        }
    }

}