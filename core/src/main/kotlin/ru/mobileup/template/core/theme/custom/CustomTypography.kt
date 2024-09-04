package ru.mobileup.template.core.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class CustomTypography(
    val title: TitleTypography,
    val body: BodyTypography,
    val caption: CaptionTypography,
    val button: ButtonTypography,
    val cryptocurrencyTypography: CryptocurrencyTypography
)

data class TitleTypography(
    val regular: TextStyle
)

data class BodyTypography(
    val regular: TextStyle,
    val medium: TextStyle
)

data class CaptionTypography(
    val regular: TextStyle
)

data class ButtonTypography(
    val bold: TextStyle
)

data class CoinTypography(
    val name: TextStyle,
    val price: TextStyle,
    val symbol: TextStyle,
    val priceChange: TextStyle
)

data class CryptocurrencyTypography(
    val toolbarTitle: TextStyle,
    val mediumText: TextStyle,
    val capsButton: TextStyle,
    val chip: TextStyle,
    val coinTypography: CoinTypography
)

val LocalCustomTypography = staticCompositionLocalOf<CustomTypography?> { null }
