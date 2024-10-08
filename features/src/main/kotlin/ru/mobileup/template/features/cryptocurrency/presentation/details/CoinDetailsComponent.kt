package ru.mobileup.template.features.cryptocurrency.presentation.details

import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.core.utils.LoadableState
import ru.mobileup.template.features.cryptocurrency.domain.CoinDetails

interface CoinDetailsComponent {

    val coinState: StateFlow<LoadableState<CoinDetails>>

    fun onRetryClick()

}