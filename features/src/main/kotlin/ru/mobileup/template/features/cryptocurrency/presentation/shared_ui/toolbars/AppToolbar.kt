package ru.mobileup.template.features.cryptocurrency.presentation.shared_ui.toolbars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.features.cryptocurrency.presentation.shared_ui.extensions.dropShadowOutOfBounds

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    shadowElevation: Dp = 2.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        content = content,
        modifier = modifier
            .fillMaxWidth()
            .dropShadowOutOfBounds(
                spread = shadowElevation,
                shape = RectangleShape,
            )
            .background(MaterialTheme.colorScheme.background)
    )
}
