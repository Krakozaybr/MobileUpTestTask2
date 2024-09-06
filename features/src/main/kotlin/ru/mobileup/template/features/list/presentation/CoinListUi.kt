package ru.mobileup.template.features.list.presentation

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
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.core.utils.PagedState
import ru.mobileup.template.core.widget.lce.CryptoPullRefreshLce
import ru.mobileup.template.features.list.domain.CoinInfo
import ru.mobileup.template.features.list.domain.CoinList
import ru.mobileup.template.features.list.presentation.ui.CoinItemList
import ru.mobileup.template.features.list.presentation.ui.CoinListToolbar
import ru.mobileup.template.features.list.presentation.ui.CurrencyChipList
import ru.mobileup.template.features.list.presentation.ui.RefreshFailedMessage

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

            Crossfade(
                targetState = currencyState.data,
                modifier = Modifier
                    .fillMaxWidth(),
                label = "Currency list state crossfade",
            ) {
                it ?: return@Crossfade

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
        }

        val coinsState by component.coins.collectAsState()

        CryptoPullRefreshLce(
            state = coinsState,
            onRefresh = component::onRefresh,
            onRetryClick = component::onRetryClick,
            content = { data, _ ->
                Box {
                    ListContent(
                        showDetails = component::onCoinClick,
                        coins = data,
                        pagedState = coinsState,
                        onLoadNext = component::onLoadNext
                    )
                    RefreshFailedMessage(
                        component = component.messageComponent,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(10.dp)
                    )
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListContent(
    showDetails: (CoinInfo) -> Unit,
    coins: CoinList,
    pagedState: PagedState<*>,
    onLoadNext: () -> Unit,
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
            pagedState = pagedState,
            onLoadNext = onLoadNext,
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