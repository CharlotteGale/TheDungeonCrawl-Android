package com.example.thedungeoncrawl.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.thedungeoncrawl.R

val RobotoMono = FontFamily(
    Font(R.font.roboto_mono)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = RobotoMono,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = RobotoMono,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = RobotoMono,
        fontSize = 12.sp
    )
)