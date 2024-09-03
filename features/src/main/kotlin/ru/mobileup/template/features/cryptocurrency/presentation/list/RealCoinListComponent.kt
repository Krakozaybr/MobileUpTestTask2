package ru.mobileup.template.features.cryptocurrency.presentation.list

import com.arkivanov.decompose.ComponentContext
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.core.utils.componentScope
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.cryptocurrency.data.CoinRepository
import ru.mobileup.template.features.cryptocurrency.data.CurrencyRepository
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

    override val currencies = MutableStateFlow(
        LoadableState(data = defaultCurrencies)
    )

    init {
        componentScope.launch {
            currenciesReplica.observe(this@RealCoinListComponent, errorHandler).collect {
                if (it.data != null) {
                    currencies.value = it
                }
            }
        }
    }

    override val selectedCurrency = MutableStateFlow(defaultCurrencies.first())

    private val coinReplica = coinRepository.coinListReplica
    override val coins = coinReplica.withKey(selectedCurrency).observe(this, errorHandler)


    override fun onCoinClick(coin: CoinInfo) {
        onOutput(CoinListComponent.Output.CoinDetailsRequested(coin.id))
    }

    override fun onCurrencyClick(currency: Currency) {
        selectedCurrency.value = currency
    }

    override fun onRetryClick() {
        if (currencies.value == defaultCurrencies) {
            currenciesReplica.refresh()
        }
        coinReplica.refresh(selectedCurrency.value)
    }

    override fun onRefresh() {
        coinReplica.refresh(selectedCurrency.value)
    }

    companion object {

        private val defaultCurrencies = persistentListOf(
            Currency("RUB"),
            Currency("USD"),
        ).toImmutableList()

    }
}