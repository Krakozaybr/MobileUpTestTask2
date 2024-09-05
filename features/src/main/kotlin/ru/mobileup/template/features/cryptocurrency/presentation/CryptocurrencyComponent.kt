package ru.mobileup.template.features.cryptocurrency.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.features.cryptocurrency.presentation.details.CoinDetailsComponent
import ru.mobileup.template.features.cryptocurrency.presentation.list.CoinListComponent

interface CryptocurrencyComponent {

    val stack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

        data class CoinList(val component: CoinListComponent) : Child

        data class CoinDetails(val component: CoinDetailsComponent) : Child
    }
}