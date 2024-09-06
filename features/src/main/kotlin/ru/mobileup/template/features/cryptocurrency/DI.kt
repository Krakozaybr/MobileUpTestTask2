package ru.mobileup.template.features.cryptocurrency

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.cryptocurrency.data.CoinRepository
import ru.mobileup.template.features.cryptocurrency.data.CoinRepositoryImpl
import ru.mobileup.template.features.cryptocurrency.data.CryptocurrencyApi
import ru.mobileup.template.features.cryptocurrency.data.CurrencyRepository
import ru.mobileup.template.features.cryptocurrency.data.CurrencyRepositoryImpl
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.presentation.CryptocurrencyComponent
import ru.mobileup.template.features.cryptocurrency.presentation.RealCryptocurrencyComponent
import ru.mobileup.template.features.cryptocurrency.presentation.details.CoinDetailsComponent
import ru.mobileup.template.features.cryptocurrency.presentation.details.RealCoinDetailsComponent
import ru.mobileup.template.features.cryptocurrency.presentation.list.CoinListComponent
import ru.mobileup.template.features.cryptocurrency.presentation.list.RealCoinListComponent

val cryptocurrencyModule = module {
    single<CryptocurrencyApi> {
        get<NetworkApiFactory>().unauthorizedKtorfit.create()
    }
    single<CurrencyRepository> { CurrencyRepositoryImpl(get(), get()) }
    single<CoinRepository> { CoinRepositoryImpl(get(), get()) }
}

fun ComponentFactory.createCryptocurrencyComponent(
    componentContext: ComponentContext,
): CryptocurrencyComponent {
    return RealCryptocurrencyComponent(
        componentContext = componentContext,
        componentFactory = get()
    )
}

fun ComponentFactory.createCoinListComponent(
    componentContext: ComponentContext,
    onOutput: (CoinListComponent.Output) -> Unit,
): CoinListComponent {
    return RealCoinListComponent(
        componentContext = componentContext,
        onOutput = onOutput,
        coinRepository = get(),
        currencyRepository = get(),
        errorHandler = get(),
        componentFactory = get()
    )
}

fun ComponentFactory.createCoinDetailsComponent(
    coinId: CoinId,
    title: String,
    componentContext: ComponentContext
): CoinDetailsComponent {
    return RealCoinDetailsComponent(
        componentContext = componentContext,
        coinId = coinId,
        title = title,
        coinRepository = get(),
        errorHandler = get()
    )
}
