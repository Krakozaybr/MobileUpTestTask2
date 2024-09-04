package ru.mobileup.template.core.theme.values

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.mobileup.template.core.theme.custom.BodyTypography
import ru.mobileup.template.core.theme.custom.ButtonTypography
import ru.mobileup.template.core.theme.custom.CaptionTypography
import ru.mobileup.template.core.theme.custom.CoinTypography
import ru.mobileup.template.core.theme.custom.CryptocurrencyTypography
import ru.mobileup.template.core.theme.custom.CustomTypography
import ru.mobileup.template.core.theme.custom.TitleTypography

val AppTypography = CustomTypography(
    title = TitleTypography(
        regular = TextStyle(
            fontSize = 24.sp
        )
    ),
    body = BodyTypography(
        regular = TextStyle(
            fontSize = 16.sp
        ),
        medium = TextStyle(
            fontSize = 16.sp
        )
    ),
    caption = CaptionTypography(
        regular = TextStyle(
            fontSize = 12.sp
        )
    ),
    button = ButtonTypography(
        bold = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    ),
    cryptocurrencyTypography = CryptocurrencyTypography(
        toolbarTitle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.15.sp
        ),
        mediumText = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
        capsButton = TextStyle(
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.75.sp,
            lineHeight = 16.sp,
            fontSize = 14.sp
        ),
        chip = TextStyle(
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.25.sp,
            lineHeight = 20.sp,
            fontSize = 14.sp
        ),
        coinTypography = CoinTypography(
            name = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            ),
            price = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            priceChange = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            symbol = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        ),
    ),
)