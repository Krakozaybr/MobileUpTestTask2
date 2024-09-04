package ru.mobileup.template.features.cryptocurrency.presentation.list.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.custom.CustomTheme

@Composable
fun CurrencyChip(
    isSelected: Boolean,
    name: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(vertical = 12.dp, horizontal = 30.dp),
    shape: Shape = RoundedCornerShape(20.dp)
) {
    val background by animateColorAsState(
        targetValue = if (isSelected) {
            CustomTheme.colors.cryptocurrencyColors.main
        } else {
            MaterialTheme.colorScheme.onSurface
        }.copy(alpha = 0.12f),
        label = "CurrencyChip background color animation"
    )

    val text by animateColorAsState(
        targetValue = if (isSelected) {
            CustomTheme.colors.cryptocurrencyColors.mainVariant
        } else {
            MaterialTheme.colorScheme.onSurface
        },
        label = "CurrencyChip text color animation"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .drawBehind {
                drawRect(
                    color = background
                )
            }
            .clickable(onClick = onClick)
            .padding(paddingValues)
    ) {
        BasicText(
            text = name.uppercase(),
            color = { text },
            style = CustomTheme.typography.cryptocurrencyTypography.chip
        )
    }

}
