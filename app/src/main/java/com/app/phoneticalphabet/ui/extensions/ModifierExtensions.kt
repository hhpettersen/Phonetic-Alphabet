package com.app.phoneticalphabet.ui.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp

fun Modifier.blurEffect(visible: Boolean) =
    blur(
        radius = if (visible) 0.dp else 12.dp,
        BlurredEdgeTreatment.Unbounded,
    )
