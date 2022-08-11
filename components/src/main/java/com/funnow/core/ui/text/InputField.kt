package com.funnow.core.ui.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import com.funnow.core.ui.theme.Colors

/**
 * Container:
 *  - Outlined:
 *      Corner
 *      Stroke Width
 *      Color: Default, Activated, Error
 *  - Underlined:
 *      Stroke Width
 *      Color: Default, Fill, Activated, Error
 *
 * Text/Placeholder:
 *  - Size
 *  - Weight
 *  - Color
 */

sealed interface Container {
    var strokeWidth: Dp

    data class Outlined(
        var corner: Dp,
        override var strokeWidth: Dp,
    ) : Container

    data class Underlined(
        override var strokeWidth: Dp,
    ) : Container
}

@Composable
fun InputField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    container: Container,
    textColor: Color = Colors.defaultText,
    backgroundColor: Color = MaterialTheme.colors.background,
    defaultColor: Color = MaterialTheme.colors.onBackground,
    activatedColor: Color = MaterialTheme.colors.primary,
) {
    val containerColor = remember { mutableStateOf(defaultColor) }
    val focusRequester = FocusRequester()

    val text = remember { mutableStateOf(TextFieldValue(value)) }
    Box(
        modifier = (if (container is Container.Outlined) Modifier.border(
            container.strokeWidth,
            color = containerColor.value,
            shape = RoundedCornerShape(container.corner)
        ) else Modifier)
            .focusRequester(focusRequester)
            .onFocusChanged {
                containerColor.value = if (it.hasFocus) { activatedColor } else defaultColor
            }
            .background(color = backgroundColor)
    ) {
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onValueChanged(it.text)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = textColor,
                cursorColor = defaultColor,
//                errorCursorColor = errorColor ?: MaterialTheme.colors.error,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
        )
    }
}