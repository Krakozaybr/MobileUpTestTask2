package ru.mobileup.template.features.details

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.get
import org.koin.dsl.module
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.details.data.CoinDetailsApi
import ru.mobileup.template.features.details.data.CoinDetailsRepository
import ru.mobileup.template.features.details.data.CoinDetailsRepositoryImpl
import ru.mobileup.template.features.details.presentation.CoinDetailsComponent
import ru.mobileup.template.features.details.presentation.RealCoinDetailsComponent

val detailsModule = module {
    single<CoinDetailsApi> {
        get<NetworkApiFactory>().unauthorizedKtorfit.create()
    }
    single<CoinDetailsRepository> { CoinDetailsRepositoryImpl(get(), get()) }
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
