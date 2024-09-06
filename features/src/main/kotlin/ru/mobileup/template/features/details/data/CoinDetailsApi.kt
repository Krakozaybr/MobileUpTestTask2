package ru.mobileup.template.features.details.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import ru.mobileup.template.features.details.data.dto.CoinDetailsResponse

interface CoinDetailsApi {

    @GET("coins/{id}")
    suspend fun getDetails(@Path("id") id: String): CoinDetailsResponse
}