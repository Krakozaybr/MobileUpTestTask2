package ru.mobileup.template.core.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.mobileup.template.core.R
import ru.mobileup.template.core.utils.dispatchOnBackPressed

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.left_arrow),
    onClick: (() -> Unit)? = null,
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                onClick = {
                    if (onClick != null) {
                        onClick()
                    } else {
                        dispatchOnBackPressed(context)
                    }
                }
            )
            .padding(8.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null
        )
    }
}