package ru.mobileup.template.features.search.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import ru.mobileup.kmm_form_validation.control.InputControl
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.search.domain.SearchCoinInfo

class FakeSearchComponent : SearchComponent {

    private val scope = CoroutineScope(Dispatchers.Main)

    override val query = InputControl(
        coroutineScope = scope,
    )
    override val items = MutableStateFlow(
        LoadableState(
            data = SearchCoinInfo.MOCKS
        )
    )

    override fun onCoinInfoClick(coinInfo: SearchCoinInfo) = Unit

    override fun onRetryClick() = Unit
}