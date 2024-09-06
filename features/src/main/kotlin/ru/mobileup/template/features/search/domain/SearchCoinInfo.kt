package ru.mobileup.template.features.search.domain

import kotlinx.collections.immutable.toImmutableList
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId

data class SearchCoinInfo(
    val id: CoinId,
    val name: String,
    val imageLink: String,
    val symbol: String
) {
    companion object {
        val MOCKS = List(10) {
            SearchCoinInfo(
                id = CoinId(it.toString()),
                name = "Name $it",
                imageLink = "https://media.licdn.com/dms/image/v2/C510BAQGyuGalyYxfXQ/company-logo_200_200/company-logo_200_200/0/1631334696178?e=2147483647&v=beta&t=5TmFyg4zbmhC3J_ByYHr6aYCmFD8ZmNcpoRT8RNs2Kw",
                symbol = "${it}SS"
            )
        }.toImmutableList()
    }
}
