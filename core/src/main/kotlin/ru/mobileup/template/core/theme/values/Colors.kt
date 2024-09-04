package ru.mobileup.template.core.theme.values

import androidx.compose.ui.graphics.Color
import ru.mobileup.template.core.theme.custom.BackgroundColors
import ru.mobileup.template.core.theme.custom.ButtonColors
import ru.mobileup.template.core.theme.custom.CryptocurrencyColors
import ru.mobileup.template.core.theme.custom.CustomColors
import ru.mobileup.template.core.theme.custom.IconColors
import ru.mobileup.template.core.theme.custom.TextColors

val LightAppColors = CustomColors(
    isLight = true,
    background = BackgroundColors(
        screen = Color(0xFFFFFFFF),
        toast = Color(0xFF000000)
    ),
    text = TextColors(
        primary = Color(0xFF000000),
        secondary = Color(0xFF797979),
        invert = Color(0xFFFFFFFF),
    ),
    icon = IconColors(
        primary = Color(0xFF000000),
        secondary = Color(0xFF797979),
        invert = Color(0xFFFFFFFF),
    ),
    button = ButtonColors(
        primary = Color(0xFF6750A4),
        secondary = Color(0xFFFFFFFF),
    ),
    cryptocurrencyColors = CryptocurrencyColors(
        main = Color(0xFFFF9F00),
        onMain = Color.White,
        mainVariant = Color(0xFFFFAD25),
        coinHead = Color(0xFF525252),
        coinSymbol = Color(0xFF525252),
        coinSuccess = Color(0xFF2A9D8F),
        coinError = Color(0xFFEB5757),
        toolbarTitle = Color.Black.copy(alpha = 0.87f),
    )
)

val DarkAppColors = LightAppColors
