package ru.mobileup.template.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.theme.custom.toMaterialColors
import ru.mobileup.template.core.theme.custom.toMaterialTypography

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkAppColors
    } else {
        LightAppColors
    }
    val typography = AppTypography

    CustomTheme(
        colors = colorScheme,
        typography = typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme.toMaterialColors(),
            typography = typography.toMaterialTypography(),
            content = content
        )
    }
}