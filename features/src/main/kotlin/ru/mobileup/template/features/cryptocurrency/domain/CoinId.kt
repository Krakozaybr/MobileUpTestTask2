package ru.mobileup.template.features.cryptocurrency.domain

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class CoinId(val id: String)
