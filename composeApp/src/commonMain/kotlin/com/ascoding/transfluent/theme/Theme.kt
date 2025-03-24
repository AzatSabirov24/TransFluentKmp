package com.ascoding.transfluent.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }
internal val LocalExtendedColorScheme = staticCompositionLocalOf { LightExtendedColorScheme }

@Composable
internal fun AppTheme(
    content: @Composable () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    val isDark by isDarkState

    val colors = if (isDark) DarkExtendedColorScheme else LightExtendedColorScheme

    SystemAppearance(!isDark)

    CompositionLocalProvider(
        LocalThemeIsDark provides isDarkState,
        LocalExtendedColorScheme provides colors
    ) {
        MaterialTheme(
            colorScheme = colors.materialColors,
            content = {
                Surface(content = content)
            }
        )
    }
}

@Composable
fun ToggleThemeButton() {
    val isDarkState = LocalThemeIsDark.current
    androidx.compose.material3.Button(
        onClick = { isDarkState.value = !isDarkState.value }
    ) {
        Text("Toggle Theme")
    }
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)

data class ColorScheme(
    val materialColors: ColorScheme,
    val backgroundMain: Color,
    // Text
    val textRegular: Color,
    // TextField
    val backgroundTextField: Color
)

val LightExtendedColorScheme = ColorScheme(
    materialColors = lightColorScheme(),
    backgroundMain = MainBackgroundLight,
    // Text
    textRegular = TextRegularLight,
    // TextField
    backgroundTextField = TextFieldBackGroundLight,
)

val DarkExtendedColorScheme = ColorScheme(
    materialColors = darkColorScheme(),
    backgroundMain = MainBackgroundDark,
    // Text
    textRegular = TextRegularDark,
    // TextField
    backgroundTextField = TextFieldBackGroundDark
)
