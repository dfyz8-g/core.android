package com.funnow.core.ui.text

import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object InputFieldDefault {

    @Composable
    fun inputFieldColors(

    ): InputFieldColors {
        return InputFieldColors(

        )
    }
}

data class InputFieldColors(
    val textColor: Color? = null,
    val defaultColor: Color? = null,
    val activatedColor: Color? = null,
    val errorColor: Color? = null
)