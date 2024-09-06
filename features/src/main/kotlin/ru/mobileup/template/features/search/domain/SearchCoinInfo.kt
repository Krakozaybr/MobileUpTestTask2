package ru.mobileup.template.features.search.domain

import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId

data class SearchCoinInfo(
    val id: CoinId,
    val name: String,
    val imageLink: String,
    val symbol: String
)
