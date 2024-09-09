package ru.mobileup.template.features.search.data

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import ru.mobileup.template.features.search.data.dto.SearchResponse

interface SearchApi {

    @GET("search")
    suspend fun searchCoins(@Query("query") query: String): SearchResponse
}