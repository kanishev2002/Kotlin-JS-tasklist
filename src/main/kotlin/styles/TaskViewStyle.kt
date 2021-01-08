package styles

import kotlinx.css.*
import styled.*

object TaskViewStyle : StyleSheet("TaskViewStyle", isStatic = true) {
    val taskView by css {
        display = Display.flex
        flexDirection = FlexDirection.row
        justifyContent = JustifyContent.start
        alignItems = Align.center
    }

    val task by css {
        display = Display.flex
        flexDirection = FlexDirection.column
    }

    val taskName by css {
        fontFamily = "Helvetica Neue"
        fontSize = 20.px
    }

    val taskDescription by css {
        fontFamily = "Helvetica Neue"
        fontSize = 12.px
        fontStyle = FontStyle.italic
    }

    val taskCompletionButton by css {
        borderWidth = 0.px
        backgroundColor = Color.transparent
    }

    val completedTask by css {
        color = Color.orange
        fontSize = 20.px
    }

    val incompleteTask by css {
        color = Color.darkGray
        fontSize = 20.px
    }

    val hr by css {
        color = rgb(230, 230, 230)
        put("width", "97%")
    }
}