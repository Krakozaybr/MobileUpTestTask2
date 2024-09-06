package ru.mobileup.template.core.common_domain.cryptocurrency

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class CoinId(val value: String)
