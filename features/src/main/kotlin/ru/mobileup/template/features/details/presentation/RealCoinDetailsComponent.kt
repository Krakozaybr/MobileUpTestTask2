package ru.mobileup.template.features.details.presentation

import com.arkivanov.decompose.ComponentContext
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.computed
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.details.data.CoinDetailsRepository

class RealCoinDetailsComponent(
    componentContext: ComponentContext,
    title: String,
    coinId: CoinId,
    coinRepository: CoinDetailsRepository,
    errorHandler: ErrorHandler
) : CoinDetailsComponent, ComponentContext by componentContext {

    private val coinReplica = coinRepository.coinDetailsReplica.withKey(coinId)

    override val coinState = computed(
        coinReplica.observe(this, errorHandler)
    ) {
        CoinDetailsComponent.Model(
            title = title,
            details = it
        )
    }

    override fun onRetryClick() {
        coinReplica.refresh()
    }
}