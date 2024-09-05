package ru.mobileup.template.features.cryptocurrency.presentation.list.ui

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.CoinList
import ru.mobileup.template.features.cryptocurrency.domain.Currency
import ru.mobileup.template.features.cryptocurrency.presentation.widget.loader.Loader

@Composable
fun CoinItemList(
    onCoinClick: (CoinInfo) -> Unit,
    coinList: CoinList,
    onEndReached: () -> Unit,
    showLoadingNext: Boolean,
    modifier: Modifier = Modifier,
    userScrollEnabled: Boolean = true,
    contentPaddingValues: PaddingValues = PaddingValues(vertical = 20.dp),
    spacing: Dp = 0.dp
) {

    val listAlpha by animateFloatAsState(
        targetValue = if (!userScrollEnabled) 0.7f else 1f,
        label = "List alpha animation"
    )
    val listState = rememberLazyListState()
    val onEndReachedState by rememberUpdatedState(onEndReached)

    LaunchedEffect(listState) {
        snapshotFlow { listState.canScrollForward }.collectLatest {
            if (!it) onEndReachedState()
        }
    }

    LazyColumn(
        modifier = modifier.graphicsLayer { alpha = listAlpha },
        state = listState,
        contentPadding = contentPaddingValues,
        userScrollEnabled = userScrollEnabled,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        items(coinList.list, key = { it.id.value }) {
            CoinItem(
                modifier = Modifier
                    .clickable { onCoinClick(it) }
                    .padding(
                        vertical = 12.dp,
                        horizontal = 20.dp
                    ),
                coin = it,
            )
        }

        if (showLoadingNext) {
            item {
                ListLoader()
            }
        }
    }
}

@Composable
private fun LazyItemScope.ListLoader(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillParentMaxWidth()
            .height(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Loader()
    }
}

@Preview
@Composable
private fun CoinItemListPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            CoinItemList(
                userScrollEnabled = true,
                onCoinClick = {},
                coinList = CoinList(
                    hasNextPage = false,
                    list = List(10) {
                        CoinInfo(
                            id = CoinId("id$it"),
                            priceChangePercentage = Math.pow(-1.0, it.toDouble())
                                .toFloat() * it * 2,
                            name = "$it name",
                            imageLink = "https://media.licdn.com/dms/image/v2/C510BAQGyuGalyYxfXQ/company-logo_200_200/company-logo_200_200/0/1631334696178?e=2147483647&v=beta&t=5TmFyg4zbmhC3J_ByYHr6aYCmFD8ZmNcpoRT8RNs2Kw",
                            price = it * 1000f,
                            symbol = "${it}SB",
                            currency = Currency("RUB")
                        )
                    }.toImmutableList()
                ),
                showLoadingNext = false,
                onEndReached = {}
            )
        }
    }
}
