package ru.mobileup.template.features.cryptocurrency.domain

import kotlinx.collections.immutable.ImmutableList

data class CoinList(
    val list: ImmutableList<CoinInfo>,
    val hasNextPage: Boolean
)
