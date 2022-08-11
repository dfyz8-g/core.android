package com.funnow.core.ui.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    container: Container,
    textColor: Color = LocalContentColor.current,
    backgroundColor: Color = MaterialTheme.colors.background,
    defaultColor: Color = MaterialTheme.colors.onBackground,
    activatedColor: Color = MaterialTheme.colors.primary,
) {
    val text = remember { mutableStateOf(TextFieldValue(value)) }
    val corner =
        if (container is Container.Outlined) RoundedCornerShape(container.corner) else RectangleShape
    val hasFocus = remember { mutableStateOf(false) }
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    val focusRequester = FocusRequester()
    val colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
        textColor = textColor,
        cursorColor = activatedColor,
//                errorCursorColor = errorColor ?: MaterialTheme.colors.error,
        unfocusedIndicatorColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent
    )
    val textFieldShape = TextFieldDefaults.TextFieldShape

    // TODO: Determine from error text
    val isError = false

    Box(
        modifier = (if (container is Container.Outlined) Modifier.border(
            container.strokeWidth,
            color = if (hasFocus.value) activatedColor else defaultColor,
            shape = corner
        ) else Modifier)
            .focusRequester(focusRequester)
            .onFocusChanged {
                hasFocus.value = it.hasFocus
            }
            .background(color = backgroundColor, shape = corner)
    ) {
        // Modifier.width(IntrinsicSize.Min) makes Spacer fillMaxWidth() to fill the width of TextField.
        Column(Modifier.width(IntrinsicSize.Min)) {
            BasicTextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    onValueChanged(it.text)
                },
                modifier
                    .background(Color.Transparent, textFieldShape)
                    .indicatorLine(enabled, isError, interactionSource, colors)
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = 40.dp
                    ),
                cursorBrush = SolidColor(colors.cursorColor(isError).value),
                singleLine = singleLine,
                decorationBox = @Composable { innerTextField ->
                    // places leading icon, text field with label and placeholder, trailing icon
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = text.value.text,
                        visualTransformation = VisualTransformation.None,
                        innerTextField = innerTextField,
                        singleLine = singleLine,
                        enabled = enabled,
                        interactionSource = interactionSource,
                        colors = colors,
                        contentPadding = PaddingValues(
                            horizontal =
                            if (container is Container.Outlined) 12.dp else 0.dp,
                            vertical = 8.dp
                        )
                    )
                }
            )
            if (container is Container.Underlined) Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(container.strokeWidth)
                    .background(color = if (hasFocus.value) activatedColor else defaultColor)
            )
        }
    }
}