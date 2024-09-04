package ru.mobileup.template.features.cryptocurrency.presentation.list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.features.R
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency
import ru.mobileup.template.features.cryptocurrency.presentation.shared_ui.utils.englishFormat
import ru.mobileup.template.features.cryptocurrency.presentation.shared_ui.utils.symbol
import kotlin.math.absoluteValue

@Composable
fun CoinItem(
    coin: CoinInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CoinImage(
            imageLink = coin.imageLink,
            contentDescription = coin.name,
            modifier = Modifier.fillMaxHeight()
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row {
                CoinName(name = coin.name)
                Spacer(modifier = Modifier.weight(1f))
                CoinPrice(currency = coin.currency, price = coin.price)
            }
            Row {
                CoinSymbol(symbol = coin.symbol)
                Spacer(modifier = Modifier.weight(1f))
                CoinPriceChange(change = coin.priceChangePercentage)
            }
        }
    }
}

@Composable
private fun CoinName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        style = CustomTheme.typography.cryptocurrencyTypography.coinTypography.name,
        color = CustomTheme.colors.cryptocurrencyColors.coinHead,
        modifier = modifier
    )
}

@Composable
private fun CoinPrice(
    currency: Currency,
    price: Float,
    modifier: Modifier = Modifier
) {
    Text(
        text = "${currency.symbol} ${price.englishFormat()}",
        style = CustomTheme.typography.cryptocurrencyTypography.coinTypography.price,
        color = CustomTheme.colors.cryptocurrencyColors.coinHead,
        modifier = modifier
    )
}

@Composable
private fun CoinSymbol(
    symbol: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = symbol,
        style = CustomTheme.typography.cryptocurrencyTypography.coinTypography.symbol,
        color = CustomTheme.colors.cryptocurrencyColors.coinSymbol,
        modifier = modifier
    )
}

@Composable
private fun CoinPriceChange(
    change: Float,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(
            id = if (change < 0) {
                R.string.cryptocurrency_price_change_up
            } else {
                R.string.cryptocurrency_price_change_down
            },
            formatArgs = arrayOf(
                change.absoluteValue.englishFormat()
            )
        ),
        style = CustomTheme.typography.cryptocurrencyTypography.coinTypography.priceChange,
        color = if (change < 0) {
            CustomTheme.colors.cryptocurrencyColors.coinError
        } else {
            CustomTheme.colors.cryptocurrencyColors.coinSuccess
        },
        modifier = modifier
    )
}

@Composable
private fun CoinImage(
    imageLink: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier.aspectRatio(1f),
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageLink)
            .crossfade(true)
            .placeholder(R.drawable.placeholder)
            .build(),
        contentDescription = contentDescription
    )
}
