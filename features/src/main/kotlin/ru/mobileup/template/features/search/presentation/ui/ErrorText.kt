package ru.mobileup.template.features.search.presentation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorText(
    errorText: String,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(horizontal = 0.dp)
) {
    Text(
        modifier = modifier.padding(paddingValues),
        text = errorText,
        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.error)
    )
}