package components

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.browser.sessionStorage
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.Serializable
import org.w3c.dom.HTMLInputElement
import react.RProps
import react.RSetState
import react.dom.button
import react.dom.div
import react.dom.input
import react.functionalComponent
import react.useState

external interface RegisterProps: RProps {
    var setRegister: RSetState<Boolean>
}

object AppClient {
    val default = HttpClient(Js) {
        install(JsonFeature){
            serializer = KotlinxSerializer()
        }
    }
}

@Serializable
data class RegisterResponse(val login: String, val userId: Int)

@Serializable
data class RegisterRequest(val login: String, val password: String)

suspend fun registerUser(login: String, password: String, setRegister: RSetState<Boolean>){
    if(login.isEmpty() || password.isEmpty()){
        window.alert("Error. Login or password is empty")
        return
    }

    val client = AppClient.default
    try {
        val response: RegisterResponse = client.post("http://localhost:8080/users/register/") {
            contentType(ContentType.Application.Json)
            body = RegisterRequest(login, password)
        }
        window.alert("Success!")
        sessionStorage.setItem("userId", response.userId.toString())
        println("Successfully registered user with login ${response.login} and id ${response.userId}")
        setRegister(false)
    }
    catch (e: Exception){
        window.alert("Something went wrong")
    }
}

val register = functionalComponent<RegisterProps> { props ->
    val (login, setLogin) = useState("")
    val (password, setPassword) = useState("")
    div (classes = "loginContainer") {
        div (classes = "loginForm"){
            input (classes = "inputBorder") {
                attrs {
                    placeholder = "Create Login"
                    onChangeFunction = {
                        val value = (it.target as? HTMLInputElement)?.value
                        setLogin(value ?: "")
                    }
                }
            }
            input (classes = "inputBorder") {
                attrs {
                    placeholder = "Create Password"
                    onChangeFunction = {
                        val value = (it.target as? HTMLInputElement)?.value
                        setPassword(value ?: "")
                    }
                }
            }
            button (classes = "signUpButton") {
                +"Sign Up"
                attrs {
                    onClickFunction = {
                        GlobalScope.launch {
                            registerUser(login, password, props.setRegister)
                        }
                    }
                }
            }
        }
    }
}