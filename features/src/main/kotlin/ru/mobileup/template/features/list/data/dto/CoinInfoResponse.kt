package ru.mobileup.template.features.list.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import ru.mobileup.template.features.list.domain.CoinInfo
import ru.mobileup.template.features.list.domain.Currency

@Serializable
data class CoinInfoResponse(
    @SerialName("id") val id: String,
    @SerialName("price_change_percentage_24h") val priceChangePercentage: Float,
    @SerialName("name") val name: String,
    @SerialName("image") val imageLink: String,
    @SerialName("current_price") val price: Float,
    @SerialName("symbol") val symbol: String,
) {
    fun map(currency: Currency) = CoinInfo(
        id = CoinId(id),
        priceChangePercentage = priceChangePercentage,
        name = name,
        imageLink = imageLink,
        price = price,
        symbol = symbol,
        currency = currency
    )
}