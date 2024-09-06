package ru.mobileup.template.features.list.domain

import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId

data class CoinInfo(
    val id: CoinId,
    val priceChangePercentage: Float,
    val name: String,
    val imageLink: String,
    val price: Float,
    val symbol: String,
    val currency: Currency
)