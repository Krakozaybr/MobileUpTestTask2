package ru.mobileup.template.features.details.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.details.domain.CoinDetails

interface CoinDetailsComponent {

    val coinState: StateFlow<Model>

    fun onRetryClick()

    data class Model(
        val details: LoadableState<CoinDetails>,
        val title: String
    )
}