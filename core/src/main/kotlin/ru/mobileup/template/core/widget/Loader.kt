package ru.mobileup.template.core.widget

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import ru.mobileup.template.core.theme.custom.CustomTheme

@Composable
fun Loader(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth,
    trackColor: Color = ProgressIndicatorDefaults.circularTrackColor,
    strokeCap: StrokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
    color: Color = CustomTheme.colors.cryptocurrencyColors.main
) {
    CircularProgressIndicator(
        modifier = modifier,
        strokeWidth = strokeWidth,
        trackColor = trackColor,
        strokeCap = strokeCap,
        color = color,
    )
}
