package com.app.phoneticalphabet.ui.screens.result

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ResultScreen(
    onFinish: () -> Unit,
) {
    val viewModel = hiltViewModel<ResultViewModel>()
}