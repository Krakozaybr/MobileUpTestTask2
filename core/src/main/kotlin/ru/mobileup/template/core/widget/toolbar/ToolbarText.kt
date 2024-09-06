package ru.mobileup.template.core.widget.toolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.theme.custom.CustomTheme

@Composable
fun ToolbarText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = CustomTheme.colors.cryptocurrencyColors.toolbarTitle,
        style = CustomTheme.typography.cryptocurrencyTypography.toolbarTitle,
        modifier = modifier
    )
}

@Preview
@Composable
private fun ToolbarTextPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ToolbarText(
                text = "Some toolbar",
                modifier = Modifier
            )
        }
    }
}
