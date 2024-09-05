package ru.mobileup.template.features.cryptocurrency.presentation.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.charlex.compose.material3.HtmlText
import ru.mobileup.template.core.theme.custom.CustomTheme

@Composable
fun CoinDetailsTextBlock(
    title: String,
    text: String,
    modifier: Modifier = Modifier,
    spacing: Dp = 12.dp
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        CoinDetailsSubtitle(title = title)
        CoinDetailsText(text = text)
    }
}

@Composable
fun CoinDetailsSubtitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = CustomTheme.typography.body.regular,
        modifier = modifier
    )
}

@Composable
fun CoinDetailsText(
    text: String,
    modifier: Modifier = Modifier
) {
    HtmlText(
        text = text,
        style = CustomTheme.typography.body.regular,
        modifier = modifier
    )
}
