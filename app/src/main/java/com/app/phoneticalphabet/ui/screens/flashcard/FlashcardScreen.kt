package com.app.phoneticalphabet.ui.screens.flashcard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FlashCardScreen(
    onViewStats: () -> Unit,
    onEndFlashcards: () -> Unit,
) {
    val viewModel = hiltViewModel<FlashcardViewModel>()
    val state = viewModel.state.collectAsState()

    FlashcardContent(
        state = state.value,
        onNextWordClicked = { viewModel.onNextWord() },
        onNewRound = { viewModel.onNewRound() },
        onViewStats = onViewStats,
        onEndFlashcards = onEndFlashcards
    )
}
