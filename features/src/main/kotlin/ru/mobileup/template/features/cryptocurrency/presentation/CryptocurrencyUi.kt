package ru.mobileup.template.features.cryptocurrency.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.cryptocurrency.presentation.CryptocurrencyComponent.Child.CoinDetails
import ru.mobileup.template.features.cryptocurrency.presentation.CryptocurrencyComponent.Child.CoinList
import ru.mobileup.template.features.cryptocurrency.presentation.details.ui.CoinDetailsUi
import ru.mobileup.template.features.cryptocurrency.presentation.list.CoinListUi

@Composable
fun CryptocurrencyUi(
    component: CryptocurrencyComponent,
    modifier: Modifier = Modifier
) {

    val childStack by component.stack.collectAsState()

    Children(
        modifier = modifier,
        stack = childStack,
        animation = stackAnimation(slide())
    ) {
        when (val instance = it.instance) {
            is CoinDetails -> CoinDetailsUi(component = instance.component)
            is CoinList -> CoinListUi(component = instance.component)
        }
    }

}

@Preview
@Composable
private fun CryptocurrencyUiPreview() {
    AppTheme {
        CryptocurrencyUi(component = FakeCryptocurrencyComponent())
    }
}