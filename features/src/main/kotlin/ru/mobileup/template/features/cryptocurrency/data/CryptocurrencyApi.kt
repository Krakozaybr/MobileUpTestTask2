package ru.mobileup.template.features.cryptocurrency.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import ru.mobileup.template.features.cryptocurrency.data.dto.CoinDetailsResponse
import ru.mobileup.template.features.cryptocurrency.data.dto.CoinInfoResponse

interface CryptocurrencyApi {

    @GET("simple/supported_vs_currencies")
    suspend fun supportedCurrencies(): List<String>

    @GET("coins/markets")
    suspend fun getList(@Query("vs_currency") currency: String): List<CoinInfoResponse>

    @GET("coins/{id}")
    suspend fun getDetails(@Path("id") id: String): CoinDetailsResponse

}