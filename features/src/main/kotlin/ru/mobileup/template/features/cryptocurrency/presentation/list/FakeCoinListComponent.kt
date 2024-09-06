package ru.mobileup.template.features.cryptocurrency.presentation.list

import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.template.core.message.presentation.FakeMessageComponent
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency

class FakeCoinListComponent : CoinListComponent {
    override val currencies = MutableStateFlow(
        LoadableState(
            loading = false,
            data = persistentListOf(
                Currency("RUB"),
                Currency("USD"),
            ).toImmutableList()
        )
    )
    override val coins = MutableStateFlow(
        LoadableState(
            loading = false,
            data = persistentListOf(
                CoinInfo(
                    id = CoinId("btc"),
                    imageLink = "",
                    priceChangePercentage = 1f,
                    name = "Bitcoin",
                    price = 12f,
                    symbol = "btc",
                    currency = Currency("RUB")
                )
            ).toImmutableList()
        )
    )
    override val selectedCurrency = MutableStateFlow(Currency("RUB"))
    override val messageComponent = FakeMessageComponent()

    override fun onCoinClick(coinInfo: CoinInfo) = Unit

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}