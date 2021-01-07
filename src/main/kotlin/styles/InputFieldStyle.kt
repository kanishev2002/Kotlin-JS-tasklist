package styles

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import styled.*

object InputFieldStyle : StyleSheet("InputFieldStyle", isStatic = true) {
    private val blackShadow = rgba(0, 0, 0, 0.2)
    private val whiteShadow = rgba(255, 255, 255, 0.7)

    val inputField by css {
        padding(5.px)
    }

    val inputBorder by css {
        borderWidth = 0.px
        borderRadius = 5.px
        boxShadow(blackShadow, 5.px, 5.px, 6.px)
        boxShadow(whiteShadow, (-5).px, (-4).px, 6.px)
    }
}