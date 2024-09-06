package ru.mobileup.template.features.root.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import kotlinx.coroutines.flow.StateFlow
import ru.mobileup.template.features.details.presentation.CoinDetailsComponent
import ru.mobileup.template.features.list.presentation.CoinListComponent

/**
 * A root of a Decompose component tree.
 *
 * Note: Try to minimize child count in RootComponent. It should operate by flows of screens rather than separate screens.
 */
interface RootComponent {

    val stack: StateFlow<ChildStack<*, Child>>

    sealed interface Child {

        data class CoinList(val component: CoinListComponent) : Child

        data class CoinDetails(val component: CoinDetailsComponent) : Child
    }
}
