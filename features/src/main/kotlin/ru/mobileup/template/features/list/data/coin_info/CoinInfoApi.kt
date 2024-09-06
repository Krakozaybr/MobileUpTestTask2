package ru.mobileup.template.features.list.data.coin_info

import androidx.annotation.IntRange
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import ru.mobileup.template.features.list.data.dto.CoinInfoResponse

interface CoinInfoApi {

    @GET("coins/markets")
    suspend fun getList(
        @Query("vs_currency") currency: String,
        @Query("page") page: Int,

        @IntRange(from = 1, to = 250)
        @Query("per_page") pageSize: Int
    ): List<CoinInfoResponse>
}