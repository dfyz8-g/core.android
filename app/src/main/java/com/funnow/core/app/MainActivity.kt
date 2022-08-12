package com.funnow.core.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                        onValueChanged = { },
                        container = Container.Underlined(
                            strokeWidth = 12.dp,
                        ),
                        labelText = { Text("Label", modifier = Modifier.padding(16.dp)) },
                        placeholderText = {
                            Text("Placeholder", color = Color(0xFF1122FF))
                        },
                        helperText = { Text("Helper", style = TextStyle(fontSize = 20.sp))}
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    InputField(
                        onValueChanged = { },
                        value = "Outlined",
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