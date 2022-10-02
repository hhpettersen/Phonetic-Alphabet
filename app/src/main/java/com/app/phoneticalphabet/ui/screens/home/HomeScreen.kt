package com.app.phoneticalphabet.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 104.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
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
