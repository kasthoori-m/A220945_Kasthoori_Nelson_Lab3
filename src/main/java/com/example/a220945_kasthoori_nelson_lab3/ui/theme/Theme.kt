package com.example.a220945_kasthoori_nelson_lab3.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PrimaryNavy, // Updated here!
    onPrimary = OnPrimaryWhite,
    secondary = SecondaryTeal,
    background = BackgroundGray,
    surface = SurfaceWhite,
    onSurface = TextDark
)

@Composable
fun EduQuestTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}