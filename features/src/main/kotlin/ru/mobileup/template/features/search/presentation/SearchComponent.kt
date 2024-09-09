package ru.mobileup.template.features.search.presentation

import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.kmm_form_validation.control.InputControl
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.search.domain.SearchCoinInfo

interface SearchComponent {

    val query: InputControl
    val items: StateFlow<LoadableState<ImmutableList<SearchCoinInfo>>>

    fun onCoinInfoClick(coinInfo: SearchCoinInfo)

    fun onRetryClick()

    sealed interface Output {
        data class CoinDetailsRequested(val coinInfo: SearchCoinInfo) : Output
    }
}