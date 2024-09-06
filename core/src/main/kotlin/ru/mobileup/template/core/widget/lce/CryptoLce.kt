package ru.mobileup.template.core.widget.lce

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.mobileup.template.core.utils.AbstractLoadableState
import ru.mobileup.template.core.widget.Loader
import ru.mobileup.template.core.widget.error.ErrorBanner

@Composable
fun <T : Any> CryptoLce(
    state: AbstractLoadableState<T>,
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit,
    content: @Composable (data: T, refreshing: Boolean) -> Unit
) {
    LceWidget(
        state = state,
        content = content,
        modifier = modifier,
        onError = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ErrorBanner(onRetryClick = onRetryClick)
            }
        },
        onLoading = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Loader()
            }
        }
    )
}