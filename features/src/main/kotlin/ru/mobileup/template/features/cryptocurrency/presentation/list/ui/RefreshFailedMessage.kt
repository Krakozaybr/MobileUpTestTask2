package ru.mobileup.template.features.cryptocurrency.presentation.list.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import ru.mobileup.template.core.message.presentation.FakeMessageComponent
import ru.mobileup.template.core.message.presentation.MessageComponent
import ru.mobileup.template.core.theme.AppTheme
import ru.mobileup.template.features.R

@Composable
fun RefreshFailedMessage(
    component: MessageComponent,
    modifier: Modifier = Modifier
) {

    val message by component.visibleMessage.collectAsState()

    AnimatedVisibility(
        modifier = modifier.clickable(onClick = component::onActionClick),
        visible = message != null
    ) {
        Snackbar(
            containerColor = MaterialTheme.colorScheme.error,
            contentColor = MaterialTheme.colorScheme.onError
        ) {
            Text(text = stringResource(id = R.string.cryptocurrency_snackbar_error_text))
        }
    }
}

@Preview
@Composable
fun RefreshFailedMessagePreview() {

    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.Center
        ) {
            RefreshFailedMessage(FakeMessageComponent())
        }
    }

}