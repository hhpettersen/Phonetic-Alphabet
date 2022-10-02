package com.app.phoneticalphabet.ui.screens.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ResultScreen(
    onNewGame: () -> Unit,
    onViewStats: () -> Unit,
    onFinish: () -> Unit,
) {
    val viewModel = hiltViewModel<ResultViewModel>()
    val state = viewModel.state.collectAsState()

    ResultContent(
        state = state.value,
        onNewGame = onNewGame,
        onViewStats = onViewStats,
        onCloseQuiz = onFinish
    )
}