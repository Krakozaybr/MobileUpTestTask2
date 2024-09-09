package ru.mobileup.template.features.search

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.mobileup.template.core.ComponentFactory
import ru.mobileup.template.core.network.NetworkApiFactory
import ru.mobileup.template.features.search.data.SearchApi
import ru.mobileup.template.features.search.data.SearchRepository
import ru.mobileup.template.features.search.data.SearchRepositoryImpl
import ru.mobileup.template.features.search.presentation.RealSearchComponent
import ru.mobileup.template.features.search.presentation.SearchComponent
import org.koin.core.component.get

val searchModule = module {
    single<SearchApi> {
        get<NetworkApiFactory>().unauthorizedKtorfit.create()
    }
    single<SearchRepository> {
        SearchRepositoryImpl(get(), get())
    }
}

fun ComponentFactory.createSearchComponent(
    componentContext: ComponentContext,
    onOutput: (SearchComponent.Output) -> Unit,
): SearchComponent {
    return RealSearchComponent(
        componentContext,
        onOutput,
        get(),
        get()
    )
}
