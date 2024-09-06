package ru.mobileup.template.features.cryptocurrency.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.createFakeChildStackStateFlow
import ru.mobileup.template.features.cryptocurrency.presentation.list.FakeCoinListComponent

class FakeCryptocurrencyComponent : CryptocurrencyComponent {
    override val stack: StateFlow<ChildStack<*, CryptocurrencyComponent.Child>> = createFakeChildStackStateFlow(
        CryptocurrencyComponent.Child.CoinList(
            component = FakeCoinListComponent()
        )
    )
}