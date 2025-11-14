package com.example.project_miniMart.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.project_miniMart.R

val robotoFontFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Companion.Normal),
    Font(R.font.roboto_bold, FontWeight.Companion.Bold),
    Font(R.font.roboto_light, FontWeight.Companion.Light),
    Font(R.font.roboto_medium, FontWeight.Companion.Medium)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

