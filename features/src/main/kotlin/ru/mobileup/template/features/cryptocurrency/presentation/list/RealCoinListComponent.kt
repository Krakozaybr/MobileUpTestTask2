package ru.mobileup.template.features.cryptocurrency.presentation.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.createMessageComponent
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.componentScope
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.cryptocurrency.data.CoinRepository
import ru.mobileup.template.features.cryptocurrency.data.CurrencyRepository
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency

class RealCoinListComponent(
    componentContext: ComponentContext,
    private val onOutput: (CoinListComponent.Output) -> Unit,
    componentFactory: ComponentFactory,
    currencyRepository: CurrencyRepository,
    coinRepository: CoinRepository,
    errorHandler: ErrorHandler,
) : CoinListComponent, ComponentContext by componentContext {

    companion object {
        private const val MESSAGE_COMPONENT = "MESSAGE_COMPONENT"
    }

    private val currenciesReplica = currencyRepository.currenciesReplica

    override val currencies = currenciesReplica
        .observe(this, errorHandler)

    override val selectedCurrency: MutableStateFlow<Currency?> = MutableStateFlow(null)

    init {
        componentScope.launch {
            selectedCurrency.value = currencies
                .map {
                    it.data?.getOrNull(0)
                }
                .filterNotNull()
                .first()
        }
    }

    private val coinReplica = coinRepository.coinListReplica.withKey(selectedCurrency)
    override val coins = coinReplica.observe(this, errorHandler)

    override val messageComponent = componentFactory.createMessageComponent(
        componentContext = childContext(MESSAGE_COMPONENT),
    )

    override fun onCoinClick(coinInfo: CoinInfo) {
        onOutput(CoinListComponent.Output.CoinDetailsRequested(coinInfo))
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