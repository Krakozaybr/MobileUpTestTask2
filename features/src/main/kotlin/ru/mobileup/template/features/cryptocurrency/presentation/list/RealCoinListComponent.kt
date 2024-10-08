package ru.mobileup.template.features.cryptocurrency.presentation.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.aartikov.replica.algebra.normal.map
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.componentScope
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.cryptocurrency.data.CoinRepository
import ru.mobileup.template.features.cryptocurrency.data.CurrencyRepository
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency

class RealCoinListComponent(
    componentContext: ComponentContext,
    currencyRepository: CurrencyRepository,
    coinRepository: CoinRepository,
    errorHandler: ErrorHandler,
    private val onOutput: (CoinListComponent.Output) -> Unit
) : CoinListComponent, ComponentContext by componentContext {

    private val currenciesReplica = currencyRepository.currenciesReplica

    override val currencies = currenciesReplica
        .observe(this, errorHandler)

    override val selectedCurrency: MutableStateFlow<Currency?> = MutableStateFlow(null)

    init {
        componentScope.launch {
            selectedCurrency.value = currencies.first().data?.getOrNull(0)
        }
    }

    private val coinReplica = coinRepository.coinListReplica.withKey(selectedCurrency)
    override val coins = coinReplica.observe(this, errorHandler)

    override fun onCoinClick(coinId: CoinId) {
        onOutput(CoinListComponent.Output.CoinDetailsRequested(coinId))
    }

    override fun onCurrencyClick(currency: Currency) {
        selectedCurrency.value = currency
    }

    override fun onRetryClick() {
        currenciesReplica.refresh()
        coinReplica.refresh()
    }

    override fun onRefresh() {
        currenciesReplica.refresh()
        coinReplica.refresh()
    }

}