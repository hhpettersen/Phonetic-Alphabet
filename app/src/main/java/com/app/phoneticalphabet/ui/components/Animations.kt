package com.app.phoneticalphabet.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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

object Animations {
    @OptIn(ExperimentalAnimationApi::class)
    fun slideInAndOut() =
        slideInHorizontally(
            animationSpec = tween(200),
            initialOffsetX = { fullWidth -> fullWidth }
        ) + fadeIn() with
                slideOutHorizontally(
                    animationSpec = tween(200),
                    targetOffsetX = { fullWidth -> -fullWidth }
                ) + fadeOut()

    @OptIn(ExperimentalAnimationApi::class)
    fun slideDown() =
        slideInVertically { height -> height } + fadeIn() with
                slideOutVertically { height -> -height } + fadeOut()

    @OptIn(ExperimentalAnimationApi::class)
    fun slideUp() =
        slideInVertically { height -> -height } + fadeIn() with
                slideOutVertically { height -> height } + fadeOut()
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
