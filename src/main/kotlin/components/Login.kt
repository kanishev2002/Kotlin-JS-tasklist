package components

import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.browser.sessionStorage
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.Serializable
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.button
import react.dom.div
import react.dom.input

external interface LoginProps : RProps {
    var setLoggedIn: (Boolean) -> Unit
}

@Serializable
data class LoginRequest(val login: String, val password: String)

@Serializable
data class LoginResponse(val login: String, val userId: Int)

suspend fun loginUser(login: String, password: String, setLoggedIn: (Boolean) -> Unit) {
    if(login.isEmpty() || password.isEmpty()){
        window.alert("Login or password is empty!")
        return
    }

    val client = AppClient.default
    try {
        val response: LoginResponse = client.post("http://localhost:8080/users/login/") {
            contentType(ContentType.Application.Json)
            body = LoginRequest(login, password)
        }
        sessionStorage.setItem("userId", response.userId.toString())
        println("Successfully logged in user with login ${response.login} and id ${response.userId}")
        setLoggedIn(true)
    }
    catch (e: Exception){
        window.alert("Something went wrong")
    }
}


val login = functionalComponent<LoginProps> { props ->
    useEffect {
        val savedToken = sessionStorage.getItem("token")
        if(!savedToken.isNullOrEmpty()){
            props.setLoggedIn(true)
        }
    }
    val (login, setLogin) = useState("")
    val (password, setPassword) = useState("")
    val (signUp, setSignUp) = useState(false)
    child(header) {}
    if(!signUp) {
        div (classes = "loginContainer"){
            div(classes = "loginForm") {
                input(classes = "inputBorder") {
                    attrs {
                        placeholder = "Login"
                        type = InputType.text
                        onChangeFunction = {
                            val value = (it.target as? HTMLInputElement)?.value
                            setLogin(value ?: "")
                        }
                    }
                }
                input(classes = "inputBorder") {
                    attrs {
                        placeholder = "Password"
                        type = InputType.password
                        onChangeFunction = {
                            val value = (it.target as? HTMLInputElement)?.value
                            setPassword(value ?: "")
                        }
                    }
                }
                button(classes = "signInButton") {
                    +"Sign in"
                    attrs {
                        onClickFunction = {
                            GlobalScope.launch {
                                loginUser(login, password, props.setLoggedIn)
                            }
                        }
                    }
                }
                button(classes = "signUpButton") {
                    +"Not registered yet? Sign up!"
                    attrs {
                        onClickFunction = {
                            setSignUp(true)
                        }
                    }
                }
            }
        }
    }
    else {
        child(register){
            attrs {
                setRegister = setSignUp
            }
        }
    }
}