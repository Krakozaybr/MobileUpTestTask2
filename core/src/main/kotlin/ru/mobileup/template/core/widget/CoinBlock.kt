package ru.mobileup.template.core.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.mobileup.template.core.R
import ru.mobileup.template.core.theme.custom.CustomTheme
import ru.mobileup.template.core.utils.englishFormat
import kotlin.math.absoluteValue

@Composable
fun CoinBlock(
    imageLink: String,
    name: String,
    symbol: String,
    modifier: Modifier = Modifier,
    currencySymbol: String? = null,
    price: Float? = null,
    priceChangePercentage: Float? = null,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CoinImage(
            imageLink = imageLink,
            contentDescription = name,
            modifier = Modifier.fillMaxHeight()
        )
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Row {
                CoinName(name = name, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(16.dp))
                if (currencySymbol != null && price != null) {
                    CoinPrice(currencySymbol = currencySymbol, price = price)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row {
                CoinSymbol(symbol = symbol)
                Spacer(modifier = Modifier.weight(1f))
                priceChangePercentage?.let {
                    CoinPriceChange(change = it)
                }
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
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
private fun CoinPrice(
    currencySymbol: String,
    price: Float,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$currencySymbol ${price.englishFormat()}",
        style = CustomTheme.typography.cryptocurrencyTypography.coinTypography.price,
        color = CustomTheme.colors.cryptocurrencyColors.coinHead,
        modifier = modifier,
        maxLines = 1,
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
        modifier = modifier,
        maxLines = 1,
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
                R.string.price_change_up
            } else {
                R.string.price_change_down
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
        modifier = modifier,
        maxLines = 1,
    )
}

@Composable
private fun CoinImage(
    imageLink: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier
            .size(50.dp)
            .aspectRatio(1f),
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageLink)
            .crossfade(true)
            .placeholder(R.drawable.placeholder)
            .build(),
        contentDescription = contentDescription
    )
}
