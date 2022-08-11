package com.funnow.core.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.funnow.core.app.ui.theme.FunNowTheme
import com.funnow.core.ui.text.Container
import com.funnow.core.ui.text.InputField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FunNowTheme {
                Column {
                    InputField(
                        "Underlined",
                        onValueChanged = { },
                        container = Container.Underlined(
                            strokeWidth = 12.dp,
                        ),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    InputField(
                        "Outlined",
                        onValueChanged = { },
                        container = Container.Outlined(
                            corner = 16.dp,
                            strokeWidth = 2.dp,
                        ),
                        defaultColor = Color.Cyan,
                        activatedColor = Color.Blue,
                        backgroundColor = Color.LightGray
                    )
                }
            }
        }
    }
}