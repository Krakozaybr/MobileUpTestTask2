package ru.mobileup.template.features.root.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import ru.mobileup.template.core.utils.toStateFlow
import ru.mobileup.template.features.details.createCoinDetailsComponent
import ru.mobileup.template.features.list.createCoinListComponent
import ru.mobileup.template.features.list.presentation.CoinListComponent
import ru.mobileup.template.features.search.createSearchComponent
import ru.mobileup.template.features.search.presentation.SearchComponent

class RealRootComponent(
    componentContext: ComponentContext,
    private val componentFactory: ComponentFactory
) : ComponentContext by componentContext, RootComponent {

    private val navigation = StackNavigation<Config>()

    override val stack: StateFlow<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.CoinList,
        serializer = Config.serializer(),
        handleBackButton = true,
        childFactory = ::createChild
    ).toStateFlow(lifecycle)

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child = when (config) {
        is Config.CoinDetails -> {
            RootComponent.Child.CoinDetails(
                componentFactory.createCoinDetailsComponent(
                    coinId = config.id,
                    title = config.title,
                    componentContext = componentContext,
                )
            )
        }

        Config.CoinList -> {
            RootComponent.Child.CoinList(
                componentFactory.createCoinListComponent(
                    componentContext = componentContext,
                    onOutput = ::onCoinListOutput
                )
            )
        }

        Config.Search -> {
            RootComponent.Child.Search(
                componentFactory.createSearchComponent(
                    componentContext = componentContext,
                    onOutput = ::onSearchOutput
                )
            )
        }
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun onSearchOutput(output: SearchComponent.Output) {
        when (output) {
            is SearchComponent.Output.CoinDetailsRequested -> {
                navigation.pushNew(
                    Config.CoinDetails(
                        id = output.coinInfo.id,
                        title = output.coinInfo.name
                    )
                )
            }
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

            CoinListComponent.Output.SearchRequested -> {
                navigation.pushNew(
                    Config.Search
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

        @Serializable
        data object Search : Config
    }
}
