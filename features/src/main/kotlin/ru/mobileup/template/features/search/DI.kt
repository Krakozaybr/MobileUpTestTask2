package ru.mobileup.template.features.search

import org.koin.dsl.module
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.search.data.SearchApi

val searchModule = module {
    single<SearchApi> {
        get<NetworkApiFactory>().unauthorizedKtorfit.create()
    }
}
