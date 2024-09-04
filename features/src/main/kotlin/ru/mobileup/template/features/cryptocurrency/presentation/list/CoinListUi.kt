package ru.mobileup.template.features.cryptocurrency.presentation.list

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.widget.LceWidget
import ru.mobileup.template.core.widget.PullRefreshLceWidget
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.presentation.list.ui.CoinItemList
import ru.mobileup.template.features.cryptocurrency.presentation.list.ui.CoinListToolbar
import ru.mobileup.template.features.cryptocurrency.presentation.list.ui.CurrencyChipList
import ru.mobileup.template.features.cryptocurrency.presentation.list.ui.RefreshFailedMessage


@Composable
fun CoinListUi(
    component: CoinListComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {

        val selectedCurrency by component.selectedCurrency.collectAsState()

        CoinListToolbar(
            modifier = Modifier
        ) {
            val currencyState by component.currencies.collectAsState()

            LceWidget(
                state = currencyState,
                content = { currencies, _ ->
                    Crossfade(
                        modifier = Modifier
                            .fillMaxWidth(),
                        targetState = currencies,
                        label = "Currency list state crossfade"
                    ) {
                        Box(
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CurrencyChipList(
                                modifier = Modifier.fillMaxWidth(),
                                selected = selectedCurrency,
                                onChipClick = component::onCurrencyClick,
                                currencyList = it
                            )
                        }
                    }
                },
                onRetryClick = {},
                onError = {},
                onLoading = {}
            )
        }

        val coins by component.coins.collectAsState()

        PullRefreshLceWidget(
            state = coins,
            onRefresh = component::onRefresh,
            content = { data, refreshing ->
                Box {
                    ListContent(
                        showDetails = component::onCoinClick,
                        coins = data
                    )
                    RefreshFailedMessage(
                        component = component.messageComponent,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(10.dp)
                    )
                }
            },
            onRetryClick = component::onRetryClick,
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListContent(
    showDetails: (CoinInfo) -> Unit,
    coins: ImmutableList<CoinInfo>,
    modifier: Modifier = Modifier
) {
    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .clipToBounds()
            .nestedScroll(refreshState.nestedScrollConnection)
    ) {

        CoinItemList(
            onCoinClick = showDetails,
            coinList = coins,
            modifier = Modifier.fillMaxSize()
        )

    }
}

@Preview
@Composable
private fun CoinListUiPreview() {
    AppTheme {
        CoinListUi(component = FakeCoinListComponent())
    }
}