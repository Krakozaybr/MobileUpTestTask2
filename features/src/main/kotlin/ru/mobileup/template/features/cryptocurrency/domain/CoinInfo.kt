package ru.mobileup.template.features.cryptocurrency.domain

data class CoinInfo(
    val id: String,
    val priceChangePercentage: Float,
    val name: String,
    val imageLink: String,
    val price: Float,
    val symbol: String,
    val currency: Currency
)