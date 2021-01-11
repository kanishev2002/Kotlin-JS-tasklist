import react.dom.*
import kotlinx.browser.document
import react.redux.provider

fun main() {
    render(document.getElementById("root")) {
        provider(store) {
            connectedApp {}
        }
    }
}