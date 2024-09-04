package ru.mobileup.template.features.cryptocurrency.presentation.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.utils.dispatchOnBackPressed
import ru.mobileup.template.core.widget.LceWidget
import ru.mobileup.template.features.R
import ru.mobileup.template.features.cryptocurrency.domain.CoinDetails
import ru.mobileup.template.features.cryptocurrency.presentation.details.CoinDetailsComponent
import ru.mobileup.template.features.cryptocurrency.presentation.shared_ui.error.ErrorBanner
import ru.mobileup.template.features.cryptocurrency.presentation.shared_ui.loader.Loader

@Composable
fun CoinDetailsUi(
    component: CoinDetailsComponent,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        val model by component.coinState.collectAsState()
        val context = LocalContext.current

        CoinDetailsToolbar(
            title = model.title,
            onGoBackClick = { dispatchOnBackPressed(context) }
        )

        LceWidget(
            state = model.details,
            content = { it, _ ->
                CoinDetailsContent(details = it)
            },
            onError = { ErrorBanner(component::onRetryClick) },
            onLoading = { Loader() }
        )
    }
}

@Composable
private fun CoinDetailsContent(
    details: CoinDetails,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CoinDetailsImage(
                imageLink = details.imageLink,
                contentDescription = details.description,
                modifier = Modifier.fillMaxWidth(0.4f)
            )
        }
        R.string.pokemons_types
        CoinDetailsSubtitle(title = stringResource(id = R.string.cryptocurrency_description))
        CoinDetailsText(text = details.description)
        CoinDetailsSubtitle(title = stringResource(id = R.string.cryptocurrency_categories))
        CoinDetailsText(text = details.categories.joinToString(", "))
    }
}
