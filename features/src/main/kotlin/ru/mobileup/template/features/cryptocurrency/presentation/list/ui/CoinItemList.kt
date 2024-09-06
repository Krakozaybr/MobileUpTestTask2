package ru.mobileup.template.features.cryptocurrency.presentation.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency

@Composable
fun CoinItemList(
    onCoinClick: (CoinInfo) -> Unit,
    coinList: ImmutableList<CoinInfo>,
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues = PaddingValues(vertical = 20.dp),
    spacing: Dp = 0.dp
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = contentPaddingValues,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        items(coinList, key = { it.id.value }) {
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
    }
}

@Preview
@Composable
private fun CoinItemListPreview() {
    AppTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            CoinItemList(
                onCoinClick = {},
                coinList = List(10) {
                    CoinInfo(
                        id = CoinId("id$it"),
                        priceChangePercentage = Math.pow(-1.0, it.toDouble()).toFloat() * it * 2,
                        name = "$it name",
                        imageLink = "https://media.licdn.com/dms/image/v2/C510BAQGyuGalyYxfXQ/company-logo_200_200/company-logo_200_200/0/1631334696178?e=2147483647&v=beta&t=5TmFyg4zbmhC3J_ByYHr6aYCmFD8ZmNcpoRT8RNs2Kw",
                        price = it * 1000f,
                        symbol = "${it}SB",
                        currency = Currency("RUB")
                    )
                }.toImmutableList(),
            )
        }
    }
}
