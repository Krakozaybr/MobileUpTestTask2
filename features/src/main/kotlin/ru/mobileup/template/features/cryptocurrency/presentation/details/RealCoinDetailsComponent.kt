package ru.mobileup.template.features.cryptocurrency.presentation.details

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.cryptocurrency.data.CoinRepository
import ru.mobileup.template.features.cryptocurrency.domain.CoinId

class RealCoinDetailsComponent(
    componentContext: ComponentContext,
    coinId: CoinId,
    coinRepository: CoinRepository,
    errorHandler: ErrorHandler
) : CoinDetailsComponent, ComponentContext by componentContext {

    private val coinReplica = coinRepository.coinDetailsReplica.withKey(coinId)

    override val coinState = coinReplica.observe(this, errorHandler)

    override fun onRetryClick() {
        coinReplica.refresh()
    }

}