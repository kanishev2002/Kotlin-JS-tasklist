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
        div(classes = "inputField") {
            input(classes = "inputBorder") {
                attrs {
                    value = props.value
                    placeholder = props.placeholder
                    onChangeFunction = props.onChange
                }
            }
        }
    }

}