package com.app.phoneticalphabet.ui.screens.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(
    onEndGame: (Int) -> Unit,
) {
    val viewModel = hiltViewModel<QuizViewModel>()
    val state = viewModel.state.collectAsState()

    QuizContent(
        state = state.value,
        onAnswerSelected = { viewModel.onAnswerSelected(it) }
    )

    state.value.uiEvent
        .firstOrNull()?.let { event ->
            viewModel.onUiEventHandled(event.id)
            when (event) {
                is UiEvent.EndQuiz -> onEndGame(event.finalScore)
            }
        }
}
