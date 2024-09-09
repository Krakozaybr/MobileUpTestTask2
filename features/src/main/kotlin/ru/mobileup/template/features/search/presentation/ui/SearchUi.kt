package ru.mobileup.template.features.search.presentation.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.widget.BackButton
import ru.mobileup.template.core.widget.CoinBlock
import ru.mobileup.template.core.widget.lce.CryptoLce
import ru.mobileup.template.core.widget.toolbar.AppToolbar
import ru.mobileup.template.features.R
import ru.mobileup.template.features.search.domain.SearchCoinInfo
import ru.mobileup.template.features.search.presentation.FakeSearchComponent
import ru.mobileup.template.features.search.presentation.SearchComponent

private const val REFRESHING_ALPHA = 0.75f

@Composable
fun SearchUi(
    component: SearchComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AppToolbar {
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton()
                TextField(
                    modifier = Modifier,
                    inputControl = component.query,
                    placeholder = stringResource(id = R.string.search)
                )
            }
        }

        val searchResult by component.items.collectAsState()

        SearchContent(
            searchResult = searchResult,
            onRetryClick = component::onRetryClick,
            onCoinInfoClick = component::onCoinInfoClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchContent(
    searchResult: LoadableState<ImmutableList<SearchCoinInfo>>,
    onRetryClick: () -> Unit,
    onCoinInfoClick: (SearchCoinInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    CryptoLce(
        modifier = modifier,
        state = searchResult,
        onRetryClick = onRetryClick
    ) { data, refreshing ->

        val contentAlpha by animateFloatAsState(
            targetValue = if (refreshing) REFRESHING_ALPHA else 1f,
            label = "LazyColumn alpha animation when refreshing"
        )

        LazyColumn(
            modifier = Modifier.graphicsLayer { alpha = contentAlpha },
            userScrollEnabled = !refreshing,
            contentPadding = PaddingValues(vertical = 20.dp)
        ) {
            items(data, key = { it.id.value }) { info ->
                CoinBlock(
                    imageLink = info.imageLink,
                    name = info.name,
                    symbol = info.symbol,
                    placeholder = R.drawable.placeholder,
                    modifier = Modifier
                        .animateItemPlacement()
                        .clickable {
                            onCoinInfoClick(info)
                        }
                        .padding(
                            vertical = 10.dp,
                            horizontal = 20.dp
                        )
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchUiPreview() {
    AppTheme {
        Surface {
            SearchUi(component = FakeSearchComponent(), modifier = Modifier.fillMaxSize())
        }
    }
}
