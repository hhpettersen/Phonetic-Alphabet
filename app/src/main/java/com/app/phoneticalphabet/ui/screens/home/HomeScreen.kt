package com.app.phoneticalphabet.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
            Button(
                onClick = onAlphabetClicked,
            ) {
                Text(text = "Phonetic alphabet")
            }
            Button(onClick = onFlashCardsClicked) {
                Text(text = "Flashcards")
            }
            Button(onClick = onQuizClicked) {
                Text(text = "Quiz")
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
