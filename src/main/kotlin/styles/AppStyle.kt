package styles

import kotlinx.css.*
import kotlinx.css.properties.BoxShadows
import kotlinx.css.properties.boxShadow
import styled.*

object AppStyle : StyleSheet("AppStyle", isStatic = true) {
    private val offWhite = rgb(225, 225, 235)
    private val blackShadow = rgba(0, 0, 0, 0.2)
    private val whiteShadow = rgba(255, 255, 255, 0.7)

    val app by css {
        display = Display.flex
        flexDirection = FlexDirection.row
    }

    val appHeader by css {
        height = 70.px
        display = Display.flex
        alignItems = Align.center
        put("background", "linear-gradient(170deg, rgba(2,168,226,1) 37%, rgba(9,9,121,1) 100%")
        marginBottom = 10.px
    }

    val headerText by css {
        marginLeft = 5.px
        fontWeight = FontWeight.bold
        fontSize = 40.px
        color = Color.white
    }
    val inputFields by css {
        display = Display.flex
        flexDirection = FlexDirection.column
        flexGrow = 1.0
        padding(20.px)
        borderRadius = 20.px
        backgroundColor = offWhite
        height = 80.px
        alignItems = Align.center
        boxShadow(blackShadow, 10.px, 10.px, 10.px)
    }

    val taskList by css {
        display = Display.flex
        flexDirection = FlexDirection.column
        flexGrow = 4.0
    }

    val submitButton by css {
        padding(5.px)
        borderRadius = 20.px
        borderWidth = 0.px
        color = Color.black
        backgroundColor = offWhite
        boxShadow(blackShadow, 10.px, 10.px, 10.px)
        boxShadow(whiteShadow, (-10).px, (-3).px, 10.px)
        active {
            boxShadow = BoxShadows.none
            borderWidth = 1.px
            borderColor = Color.whiteSmoke
        }
    }
}