package com.example.mediaparkuabtesttask.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    //newsBoxTitleStyle
    h1 = TextStyle(
        color = black,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        textAlign = TextAlign.Left,
    ),
    h2 = TextStyle(
        color = black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 20.sp,
        textAlign = TextAlign.Left,
    ),

    h6 = TextStyle(
        color = orange,
        fontSize = 10.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 14.sp,
        textAlign = TextAlign.Left,
    ),

    subtitle1 = TextStyle(
        color = black,
        fontSize = 12.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp,
        textAlign = TextAlign.Left,
    ),

    subtitle2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 19.sp,
        textAlign = TextAlign.Left,
        color = lightGray
    ),

    body2 = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 19.sp,
        textAlign = TextAlign.Left,
        color = black
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)