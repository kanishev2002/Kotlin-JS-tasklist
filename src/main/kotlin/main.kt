import containers.connectedApp
import entities.Store
import kotlinx.browser.document
import react.dom.render
import react.redux.provider
import reducers.appReducer
import redux.createStore
import redux.rEnhancer

val store = createStore(::appReducer, Store(), rEnhancer())

fun main() {
    render(document.getElementById("root")) {
        provider(store) {
            connectedApp {}
        }
    }
}