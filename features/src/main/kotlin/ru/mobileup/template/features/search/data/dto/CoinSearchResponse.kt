package ru.mobileup.template.features.search.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import ru.mobileup.template.features.search.domain.SearchCoinInfo

@Serializable
data class SearchResponse(
    @SerialName("coins") val coins: List<CoinItemSearchResponse>
)

@Serializable
data class CoinItemSearchResponse(
    @SerialName("id") val id: CoinId,
    @SerialName("name") val name: String,
    @SerialName("large") val imageLink: String,
    @SerialName("symbol") val symbol: String
) {
    companion object {
        fun CoinItemSearchResponse.toDomain() = SearchCoinInfo(
            id = id,
            name = name,
            imageLink = imageLink,
            symbol = symbol,
        )
    }
}
