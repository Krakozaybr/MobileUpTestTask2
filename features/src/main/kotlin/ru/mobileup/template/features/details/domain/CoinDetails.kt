package ru.mobileup.template.features.details.domain

import kotlinx.collections.immutable.ImmutableList
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId

data class CoinDetails(
    val id: CoinId,
    val imageLink: String,
    val description: String?,
    val categories: ImmutableList<String>
)
