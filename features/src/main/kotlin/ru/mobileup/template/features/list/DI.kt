package ru.mobileup.template.features.list

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.list.data.coin_info.CoinInfoApi
import ru.mobileup.template.features.list.data.coin_info.CoinInfoRepository
import ru.mobileup.template.features.list.data.coin_info.CoinInfoRepositoryImpl
import ru.mobileup.template.features.list.data.currency.CurrencyApi
import ru.mobileup.template.features.list.data.currency.CurrencyRepository
import ru.mobileup.template.features.list.data.currency.CurrencyRepositoryImpl
import ru.mobileup.template.features.list.presentation.CoinListComponent
import ru.mobileup.template.features.list.presentation.RealCoinListComponent

val listModule = module {
    single<CoinInfoApi> {
        get<NetworkApiFactory>().unauthorizedKtorfit.create()
    }
    single<CurrencyApi> {
        get<NetworkApiFactory>().unauthorizedKtorfit.create()
    }
    single<CoinInfoRepository> { CoinInfoRepositoryImpl(get(), get()) }
    single<CurrencyRepository> { CurrencyRepositoryImpl(get(), get()) }
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