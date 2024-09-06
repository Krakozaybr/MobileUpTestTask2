package ru.mobileup.template.features.list.presentation

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.message.presentation.MessageComponent
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.PagedState
import ru.mobileup.template.features.list.domain.CoinInfo
import ru.mobileup.template.features.list.domain.CoinList
import ru.mobileup.template.features.list.domain.Currency

interface CoinListComponent {

    val currencies: StateFlow<LoadableState<ImmutableList<Currency>>>
    val coins: StateFlow<PagedState<CoinList>>
    val selectedCurrency: StateFlow<Currency?>
    val messageComponent: MessageComponent

    fun onCoinClick(coinInfo: CoinInfo)

    fun onCurrencyClick(currency: Currency)

    fun onRetryClick()

    fun onRefresh()

    fun onLoadNext()

    sealed interface Output {
        data class CoinDetailsRequested(val coinInfo: CoinInfo) : Output
    }
}