package com.app.phoneticalphabet.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CountDown(
    onCountFinished: () -> Unit,
) {
    var secondsToDisappear by remember { mutableStateOf(3) }

    LaunchedEffect(Unit) {
        while(secondsToDisappear > 0) {
            delay(1000)
            secondsToDisappear -= 1
        }
        onCountFinished()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedContent(
            modifier = Modifier.align(Alignment.Center),
            targetState = secondsToDisappear.toString(),
            transitionSpec = { Animations.slideDown().using(SizeTransform(clip = false)) }
        ) {
            Text(
                text = it,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
fun PreviewCountDown() {
    CountDown {

    }
}