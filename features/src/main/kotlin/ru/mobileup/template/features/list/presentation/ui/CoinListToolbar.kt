package ru.mobileup.template.features.list.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.widget.toolbar.AppToolbar
import ru.mobileup.template.core.widget.toolbar.ToolbarText
import ru.mobileup.template.features.R

@Composable
fun CoinListToolbar(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(20.dp),
    spacing: Dp = 0.dp,
    bottomContent: @Composable () -> Unit,
) {
    AppToolbar(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(spacing),
        ) {
            Row(
                modifier = Modifier.padding(paddingValues),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ToolbarText(
                    text = stringResource(id = R.string.coin_list),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(onClick = onSearchClick)
                        .padding(8.dp)
                )
            }
            bottomContent()
        }
    }
}

@Preview
@Composable
private fun CoinListToolbarPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CoinListToolbar(onSearchClick = {}) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(21.dp)
                ) {
                    repeat(10) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(Color.Gray)
                        )
                    }
                }
            }
        }
    }
}
