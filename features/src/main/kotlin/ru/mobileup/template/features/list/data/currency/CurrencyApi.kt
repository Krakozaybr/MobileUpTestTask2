package ru.mobileup.template.features.list.data.currency

import de.jensklingenberg.ktorfit.http.GET

interface CurrencyApi {

    @GET("simple/supported_vs_currencies")
    suspend fun supportedCurrencies(): List<String>
}