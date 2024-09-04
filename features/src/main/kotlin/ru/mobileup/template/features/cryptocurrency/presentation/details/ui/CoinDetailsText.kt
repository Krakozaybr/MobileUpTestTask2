package ru.mobileup.template.features.cryptocurrency.presentation.details.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import de.charlex.compose.material3.HtmlText
import ru.mobileup.template.core.theme.custom.CustomTheme

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