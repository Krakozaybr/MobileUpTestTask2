package ru.mobileup.template.features.cryptocurrency.presentation.details.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.mobileup.template.core.theme.custom.CustomTheme

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