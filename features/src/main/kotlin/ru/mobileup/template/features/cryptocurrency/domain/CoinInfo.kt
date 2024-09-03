package ru.mobileup.template.features.cryptocurrency.domain

data class CoinInfo(
    val id: CoinId,
    val priceChangePercentage: Float,
    val name: String,
    val imageLink: String,
    val price: Float,
    val symbol: String,
    val currency: Currency
)