package ru.mobileup.template.features.root.presentation

import ru.mobileup.template.core.utils.createFakeChildStackStateFlow
import ru.mobileup.template.features.list.presentation.FakeCoinListComponent

class FakeRootComponent : RootComponent {

    override val stack = createFakeChildStackStateFlow(
        RootComponent.Child.CoinList(FakeCoinListComponent())
    )
}
