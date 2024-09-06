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
) {
    companion object {
        val MOCK = CoinInfo(
            id = CoinId("coin_id"),
            priceChangePercentage = 12.23213f,
            name = "Coin name",
            imageLink = "https://media.licdn.com/dms/image/v2/C510BAQGyuGalyYxfXQ/company-logo_200_200/company-logo_200_200/0/1631334696178?e=2147483647&v=beta&t=5TmFyg4zbmhC3J_ByYHr6aYCmFD8ZmNcpoRT8RNs2Kw",
            price = 1000.2132f,
            symbol = "MCK",
            currency = Currency("RUB")
        )
    }
}