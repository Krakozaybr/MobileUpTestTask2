package ru.mobileup.template.features.list.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mobileup.template.core.widget.CoinBlock
import ru.mobileup.template.features.R
import ru.mobileup.template.features.list.domain.CoinInfo
import ru.mobileup.template.features.list.presentation.ui.utils.symbol

@Composable
fun CoinItem(
    coin: CoinInfo,
    modifier: Modifier = Modifier
) {
    CoinBlock(
        imageLink = coin.imageLink,
        name = coin.name,
        symbol = coin.symbol,
        modifier = modifier,
        currencySymbol = coin.currency.symbol,
        price = coin.price,
        priceChangePercentage = coin.priceChangePercentage,
        placeholder = R.drawable.placeholder
    )
}