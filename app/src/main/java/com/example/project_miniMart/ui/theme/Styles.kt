package com.example.project_miniMart.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

object Styles {
    val textStyleRobotoMediumSp12 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium
    )
    val textStyleRobotoBoldSp24 = TextStyle(
        fontSize = 24.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold
    )
    val textStyleRobotoRegularSp16 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal
    )
    val textStyleRobotoMediumSp16 = TextStyle(
        fontSize = 16.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium
    )

    val textStyleRobotoRegularSp14 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal
    )

    val textStyleRobotoMediumSp14 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Medium
    )


    //styles buttons

    val primaryButtonStyle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )

    val secondaryButtonStyle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    val textStyleRobotoRegularSp10 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal
    )

    val textStyleRobotoThinSp12 = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Light
    )

    val textStyleRobotoThinSp10 = TextStyle(
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Light
    )
    val textStyleRobotoBOLDSp14 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold
    )

    var textLoader = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = robotoFontFamily,
        lineHeight = 16.sp
    )

    val textStyleRobotoBoldSp26 = TextStyle(
        fontSize = 26.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold
    )
    val textStyleRobotoLightSp14 = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Light
    )
    val textStyleRobotoThinSp18 = TextStyle(
        fontSize = 18.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Thin
    )

    val bodyLegendsStyle = SpanStyle(
        color = Color.DarkGray,
        fontSize = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Thin
    )
    @Composable
    fun wordsLegendsStyle() = SpanStyle(
        color = MaterialTheme.colorScheme.primary,
        fontSize = 16.sp,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline
    )

    var roboto16Medium = TextStyle(
        fontSize = DimensSp.Sp16,
        lineHeight = DimensSp.Sp24,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Companion.Medium,
    )
    var roboto20Medium = TextStyle(
        fontSize = DimensSp.Sp20,
        lineHeight = DimensSp.Sp30,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Companion.Medium,
    )
    var roboto10Medium = TextStyle(
        fontSize = DimensSp.Sp10,
        lineHeight = DimensSp.Sp16,
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Companion.Medium,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
    var roboto16Regular = TextStyle(
        fontSize = DimensSp.Sp16,
        fontWeight = FontWeight.Companion.Normal,
        fontFamily = robotoFontFamily,
        lineHeight = DimensSp.Sp24,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
    var roboto16Light = TextStyle(
        fontSize = DimensSp.Sp16,
        fontWeight = FontWeight.Companion.Light,
        fontFamily = robotoFontFamily,
        lineHeight = DimensSp.Sp16,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )
    var roboto14Regular = TextStyle(
        fontSize = DimensSp.Sp14,
        fontWeight = FontWeight.Companion.Normal,
        fontFamily = robotoFontFamily,
        lineHeight = DimensSp.Sp18
    )


    var roboto14MediumChipChecked = TextStyle(
        fontSize = DimensSp.Sp10,
        fontWeight = FontWeight.Companion.Medium,
        fontFamily = robotoFontFamily,
        lineHeight = DimensSp.Sp16,
        color = Color.White
    )
    var roboto14MediumChipUnChecked = TextStyle(
        fontSize = DimensSp.Sp10,
        fontWeight = FontWeight.Companion.Medium,
        fontFamily = robotoFontFamily,
        lineHeight = DimensSp.Sp16,
        color = Color(0xff4a4a4a)
    )
}