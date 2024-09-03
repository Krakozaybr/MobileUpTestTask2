package ru.mobileup.template.features.cryptocurrency.presentation.list

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency

interface CoinListComponent {

    val currencies: StateFlow<LoadableState<ImmutableList<Currency>>>
    val coins: StateFlow<LoadableState<ImmutableList<CoinInfo>>>
    val selectedCurrency: StateFlow<Currency>

    fun onCoinClick(coin: CoinInfo)

    fun onCurrencyClick(currency: Currency)

    fun onRetryClick()

    fun onRefresh()

    sealed interface Output {
        data class CoinDetailsRequested(val coinId: CoinId) : Output
    }

}