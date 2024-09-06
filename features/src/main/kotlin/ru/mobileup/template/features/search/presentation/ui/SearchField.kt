package ru.mobileup.template.features.search.presentation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.localized
import kotlinx.coroutines.flow.collectLatest
import ru.mobileup.kmm_form_validation.control.InputControl
import ru.mobileup.kmm_form_validation.toCompose
import ru.mobileup.template.core.theme.custom.CustomTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextField(
    inputControl: InputControl,
    modifier: Modifier = Modifier,
    requestFocusAsEnterComposition: Boolean = true,
    style: TextStyle = CustomTheme.typography.cryptocurrencyTypography.toolbarTitle,
    placeholder: String? = null,
    errorSpacing: Dp = 5.dp,
    verticalPadding: Dp = 5.dp
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val hasFocus by inputControl.hasFocus.collectAsState()
    val error by inputControl.error.collectAsState()
    val currentValue by inputControl.text.collectAsState()

    var currentSelection by rememberSaveable(stateSaver = TextRangeSaver) {
        mutableStateOf(TextRange(0))
    }

    var currentComposition by rememberSaveable(stateSaver = NullableTextRangeSaver) {
        mutableStateOf(null)
    }

    val currentTextFieldValue by remember {
        derivedStateOf {
            TextFieldValue(currentValue, currentSelection, currentComposition)
        }
    }

    LaunchedEffect(key1 = inputControl.moveCursorEvent) {
        inputControl.moveCursorEvent.collectLatest {
            currentSelection = TextRange(it)
        }
    }

    var errorTextHeight by remember {
        mutableStateOf(0.dp)
    }

    Column(
        modifier = modifier
            .padding(
                top = errorTextHeight + verticalPadding + errorSpacing,
                bottom = verticalPadding
            )
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
    ) {
        val focusRequester = remember { FocusRequester() }

        if (requestFocusAsEnterComposition) {
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        }

        if (hasFocus) {
            SideEffect {
                focusRequester.requestFocus()
            }
        }

        LaunchedEffect(key1 = inputControl) {
            inputControl.scrollToItEvent.collectLatest {
                bringIntoViewRequester.bringIntoView()
            }
        }

        BasicTextField(
            value = currentTextFieldValue,
            textStyle = style,
            onValueChange = {
                inputControl.onTextChanged(it.text)
                currentSelection = it.selection
                currentComposition = it.composition
            },
            keyboardOptions = inputControl.keyboardOptions.toCompose(),
            singleLine = inputControl.singleLine,
            decorationBox = {
                // Placeholder
                placeholder?.let {
                    Placeholder(
                        text = it,
                        style = style,
                        isVisible = currentTextFieldValue.text.isEmpty()
                    )
                }
                it()
            },
            visualTransformation = inputControl.visualTransformation.toCompose(),
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    inputControl.onFocusChanged(it.isFocused)
                }
        )

        Spacer(modifier = Modifier.height(errorSpacing))

        val density = LocalDensity.current

        ErrorText(
            errorText = error?.localized() ?: "",
            modifier = Modifier.onSizeChanged {
                errorTextHeight = with(density) { it.height.toDp() }
            }
        )
    }
}

private const val PLACEHOLDER_ANIMATION_DURATION = 400
private const val PLACEHOLDER_DEFAULT_ALPHA = 0.65f

@Composable
private fun Placeholder(
    text: String,
    style: TextStyle,
    isVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val alphaAnimatable = remember {
        Animatable(if (isVisible) PLACEHOLDER_DEFAULT_ALPHA else 0f)
    }

    LaunchedEffect(isVisible) {
        if (isVisible) {
            alphaAnimatable.animateTo(
                targetValue = PLACEHOLDER_DEFAULT_ALPHA,
                animationSpec = tween(PLACEHOLDER_ANIMATION_DURATION)
            )
        } else {
            alphaAnimatable.snapTo(0f)
        }
    }

    Text(
        text = text,
        style = style,
        modifier = modifier
            .graphicsLayer { alpha = alphaAnimatable.value }
    )
}

private val TextRangeSaver = listSaver(
    save = { listOf(it.start, it.end) },
    restore = { TextRange(it[0], it[1]) }
)

private val NullableTextRangeSaver = listSaver<TextRange?, Int>(
    save = { if (it != null) listOf(it.start, it.end) else emptyList() },
    restore = { TextRange(it[0], it[1]) }
)