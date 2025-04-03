package com.example.tbcacademyhomework.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalColor = compositionLocalOf { Colors() }

val AppColor: Colors
    @Composable
    @ReadOnlyComposable
    get() = LocalColor.current

data class Colors(
    val canvas: Color = Color.Unspecified,
    val primary: Color = Color.Unspecified,
    val primaryLight: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val secondaryDark: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val textLight: Color = Color.Unspecified,
    val textPrimaryLight: Color = Color.Unspecified
)

val dayColorScheme = Colors(
    canvas = Color.White,
    primary = Color.Black,
    primaryLight = Color.Gray,
    secondary = Color.White,
    secondaryDark = Color.Black.copy(0.7f),
    textPrimary = Color.Black,
    textLight = Color.White,
    textSecondary = Color.Black.copy(0.4f),
    textPrimaryLight = Color.Gray
)