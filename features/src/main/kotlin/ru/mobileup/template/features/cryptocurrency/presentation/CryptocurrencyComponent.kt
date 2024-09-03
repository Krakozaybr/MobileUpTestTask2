package ru.mobileup.template.features.cryptocurrency.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mobileup.template.features.cryptocurrency.presentation.details.CoinDetailsComponent
import ru.mobileup.template.features.cryptocurrency.presentation.list.CoinListComponent

interface CryptocurrencyComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class CoinList(val component: CoinListComponent) : Child

        data class CoinDetails(val component: CoinDetailsComponent) : Child

    }

}