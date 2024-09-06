package ru.mobileup.template.features.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.createMessageComponent
import ru.mobileup.template.core.utils.toStateFlow
import ru.mobileup.template.features.cryptocurrency.createCryptocurrencyComponent
import ru.mobileup.template.features.pokemons.createPokemonsComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<ChildConfig>()

    override val childStack = childStack(
        source = navigation,
        initialConfiguration = ChildConfig.Cryptocurrency,
        serializer = ChildConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    override val messageComponent = componentFactory.createMessageComponent(
        childContext(key = "message")
    )

    private fun createChild(
        config: ChildConfig,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        ChildConfig.Pokemons -> {
            RootComponent.Child.Pokemons(
                componentFactory.createPokemonsComponent(componentContext)
            )
        }

        ChildConfig.Cryptocurrency -> {
            RootComponent.Child.Cryptocurrency(
                componentFactory.createCryptocurrencyComponent(componentContext)
            )
        }
    }

    @Serializable
    sealed interface ChildConfig {

        @Serializable
        data object Pokemons : ChildConfig

        @Serializable
        data object Cryptocurrency : ChildConfig
    }
}
