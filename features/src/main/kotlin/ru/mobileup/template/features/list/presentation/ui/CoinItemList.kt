package ru.mobileup.template.features.list.presentation.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toImmutableList
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.utils.PagedState
import ru.mobileup.template.core.utils.TriggerLoadNext
import ru.mobileup.template.core.widget.CoinBlock
import ru.mobileup.template.core.widget.Loader
import ru.mobileup.template.features.list.domain.CoinInfo
import ru.mobileup.template.features.list.domain.CoinList
import ru.mobileup.template.features.list.domain.Currency

@Composable
fun CoinItemList(
    onCoinClick: (CoinInfo) -> Unit,
    coinList: CoinList,
    pagedState: PagedState<*>,
    onLoadNext: () -> Unit,
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

    listState.TriggerLoadNext(
        pagedState = pagedState,
        hasNextPage = coinList.hasNextPage,
        callback = onLoadNext
    )

    LazyColumn(
        modifier = modifier
            .graphicsLayer { alpha = listAlpha },
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

        if (pagedState.loadingStatus == PagedLoadingStatus.LoadingNextPage) {
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
                        CoinInfo.MOCK
                    }.toImmutableList()
                ),
                onLoadNext = {},
                pagedState = PagedState<Unit>(loadingStatus = PagedLoadingStatus.LoadingNextPage)
            )
        }
    }
}
