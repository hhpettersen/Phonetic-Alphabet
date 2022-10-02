package com.app.phoneticalphabet.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.ui.theme.MainTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedExpandAndShrink(
    visible: Boolean = true,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
        exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically)
    ) {
        content()
    }
}

@Preview
@Composable
fun PreviewAnimatedExpandAndShrink() {
    var visible by remember { mutableStateOf(true) }

    MainTheme {
        Column(
            modifier = Modifier.height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { visible = !visible }) {
                Text(text = "Toggle visibility")
            }
            AnimatedExpandAndShrink(visible) {
                Text(text = "VISIBLE!")
            }
        }
    }
}
