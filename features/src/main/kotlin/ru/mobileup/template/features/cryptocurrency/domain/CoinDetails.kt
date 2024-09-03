package ru.mobileup.template.features.cryptocurrency.domain

import kotlinx.collections.immutable.ImmutableList

data class CoinDetails(
    val id: CoinId,
    val imageLink: String,
    val description: String,
    val categories: ImmutableList<String>
)
