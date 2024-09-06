package ru.mobileup.template.features.details.presentation

import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.details.domain.CoinDetails
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId

class FakeCoinDetailsComponent : CoinDetailsComponent {
    override val coinState = MutableStateFlow(
        CoinDetailsComponent.Model(
            details = LoadableState(
                data = CoinDetails(
                    id = CoinId("btc"),
                    imageLink = "https://media.licdn.com/dms/image/v2/C510BAQGyuGalyYxfXQ/company-logo_200_200/company-logo_200_200/0/1631334696178?e=2147483647&v=beta&t=5TmFyg4zbmhC3J_ByYHr6aYCmFD8ZmNcpoRT8RNs2Kw",
                    description = "Some description".trimIndent(),
                    categories = persistentListOf(
                        "Smart Contract Platform", "Ethereum Ecosystems"
                    )
                )
            ),
            title = "Bitcoin"
        )
    )

    override fun onRetryClick() = Unit
}