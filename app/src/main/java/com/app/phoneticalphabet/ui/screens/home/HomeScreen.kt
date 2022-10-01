package com.app.phoneticalphabet.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.phoneticalphabet.ui.components.StandardButton
import com.app.phoneticalphabet.ui.theme.MainTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAlphabetClicked: () -> Unit,
    onQuizClicked: () -> Unit,
    onFlashCardsClicked: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StandardButton(text = "Phonetic alphabet") {
                onAlphabetClicked()
            }
            StandardButton(text = "Flashcards") {
                onFlashCardsClicked()
            }
            StandardButton(text = "Quiz") {
                onQuizClicked()
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    MainTheme {
        HomeScreen(
            onAlphabetClicked = {},
            onQuizClicked = {},
            onFlashCardsClicked = {},
        )
    }
}
