package com.app.phoneticalphabet.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.phoneticalphabet.R

val rubik = FontFamily(
    Font(R.font.rubik_regular),
    Font(R.font.rubik_light, FontWeight.Thin),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_semi_bold, FontWeight.Medium),
)

val Typography = Typography(
    // Button content
    bodyLarge = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    displayLarge = TextStyle(
        fontFamily = rubik,
    ),
    displayMedium = TextStyle(
        fontFamily = rubik,
    ),
    displaySmall = TextStyle(
        fontFamily = rubik,
    ),
    headlineLarge = TextStyle(
        fontFamily = rubik,
    ),
    headlineMedium = TextStyle(
        fontFamily = rubik,
    ),
    // Card headline
    headlineSmall = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Nav header
    titleLarge = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = rubik,
    ),
    titleSmall = TextStyle(
        fontFamily = rubik,
    ),
    // Card content
    bodyMedium = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = rubik,
    ),
    labelMedium = TextStyle(
        fontFamily = rubik,
    ),
    labelSmall = TextStyle(
        fontFamily = rubik,
    )
)
