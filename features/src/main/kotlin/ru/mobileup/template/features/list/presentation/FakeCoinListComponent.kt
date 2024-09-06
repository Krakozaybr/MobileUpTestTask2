package ru.mobileup.template.features.list.presentation

import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import me.aartikov.replica.paged.PagedLoadingStatus
import ru.mobileup.template.core.message.presentation.FakeMessageComponent
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.PagedState
import ru.mobileup.template.features.list.domain.CoinInfo
import ru.mobileup.template.features.list.domain.CoinList
import ru.mobileup.template.features.list.domain.Currency

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
        PagedState(
            loadingStatus = PagedLoadingStatus.None,
            data = CoinList(
                hasNextPage = false,
                list = persistentListOf(
                    CoinInfo.MOCK
                ).toImmutableList()
            )
        )
    )

    override val selectedCurrency = MutableStateFlow(Currency("RUB"))
    override val messageComponent = FakeMessageComponent()

    override fun onSearchClick() = Unit

    override fun onLoadNext() = Unit

    override fun onCoinClick(coinInfo: CoinInfo) = Unit

    override fun onCurrencyClick(currency: Currency) = Unit

    override fun onRetryClick() = Unit

    override fun onRefresh() = Unit
}