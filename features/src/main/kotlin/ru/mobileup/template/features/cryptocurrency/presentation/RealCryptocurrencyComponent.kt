package ru.mobileup.template.features.cryptocurrency.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.utils.toStateFlow
import ru.mobileup.template.features.cryptocurrency.createCoinDetailsComponent
import ru.mobileup.template.features.cryptocurrency.createCoinListComponent
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.presentation.list.CoinListComponent

class RealCryptocurrencyComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : CryptocurrencyComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: StateFlow<ChildStack<*, CryptocurrencyComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.CoinList,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): CryptocurrencyComponent.Child = when (config) {
        is Config.CoinDetails -> {
            CryptocurrencyComponent.Child.CoinDetails(
                componentFactory.createCoinDetailsComponent(
                    coinId = config.id,
                    title = config.title,
                    componentContext = componentContext,
                )
            )
        }

        Config.CoinList -> {
            CryptocurrencyComponent.Child.CoinList(
                componentFactory.createCoinListComponent(
                    componentContext = componentContext,
                    onOutput = ::onCoinListOutput
                )
            )
        }
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun onCoinListOutput(output: CoinListComponent.Output) {
        when (output) {
            is CoinListComponent.Output.CoinDetailsRequested -> {
                navigation.pushNew(
                    Config.CoinDetails(
                        id = output.coinInfo.id,
                        title = output.coinInfo.name
                    )
                )
            }
        }
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object CoinList : Config

        @Serializable
        data class CoinDetails(val id: CoinId, val title: String) : Config

    }

}