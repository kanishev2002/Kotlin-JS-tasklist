import kotlinx.html.js.onChangeFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import styled.*
import styles.InputFieldStyle

external interface InputFieldProps : RProps {
    var value: String
    var onChange: (event: Event) -> Unit
    var placeholder: String
}


class InputField : RComponent<InputFieldProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                InputFieldStyle.inputField
            }
            styledInput {
                css {
                    InputFieldStyle.inputBorder
                }
                attrs {
                    value = props.value
                    placeholder = props.placeholder
                    onChangeFunction = props.onChange
                }
            }
        }
    }

}