package ru.mobileup.template.core.widget.lce

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.StringDesc
import ru.mobileup.template.core.utils.AbstractLoadableState
import ru.mobileup.template.core.widget.ErrorPlaceholder
import ru.mobileup.template.core.widget.FullscreenCircularProgress

/**
 * Displays Replica state ([AbstractLoadableState]).
 */
@Composable
fun <T : Any> LceWidget(
    state: AbstractLoadableState<T>,
    modifier: Modifier = Modifier,
    onRetryClick: (() -> Unit)? = null,
    onError: (@Composable (StringDesc) -> Unit)? = null,
    onLoading: (@Composable () -> Unit)? = null,
    content: @Composable (data: T, refreshing: Boolean) -> Unit
) {
    val loading = state.loading
    val data = state.data
    val error = state.error

    Box(modifier) {
        when {
            data != null -> content(data, loading)

            loading -> onLoading?.invoke() ?: FullscreenCircularProgress()

            error != null -> onError?.invoke(error) ?: ErrorPlaceholder(
                errorMessage = error.localized(),
                onRetryClick = onRetryClick ?: {}
            )
        }
    }
}
