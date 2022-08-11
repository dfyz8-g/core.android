package com.funnow.core.ui.theme

import androidx.compose.ui.graphics.Color

internal object ColorsPalette {
    val orange70 = Color(0xFFD21F00)
    val orange60 = Color(0xFFFF2A05)
    val orange50 = Color(0xFFFF5537)
    val orange40 = Color(0xFFFF816B)
    val orange30 = Color(0xFFFFAD9E)
    val orange20 = Color(0xFFffd8d1)
    val orange10 = Color(0xFFFFEEEB)

    val black50 = Color(0xFF252729)
    val black30 = Color(0xFF555A5F)

    val gray80 = Color(0xFF747476)
    val gray60 = Color(0xFFa7a7a9)
    val gray50 = Color(0xFFc1c1c2)
    val gray40 = Color(0xFFdbdbdb)
    val gray30 = Color(0xFFEDEDED)
    val gray20 = Color(0xFFF4F4F5)
    val gray10 = Color(0xFFFFEEEB)

    val purple50 = Color(0xFF5A69EB)

    // TODO: Migrate the colors from XML
}

internal object Colors {
    val primary = ColorsPalette.orange50
    val primaryVariant = ColorsPalette.orange70

    val secondary = ColorsPalette.purple50
    val defaultText = ColorsPalette.black50
    val background = Color.White
}