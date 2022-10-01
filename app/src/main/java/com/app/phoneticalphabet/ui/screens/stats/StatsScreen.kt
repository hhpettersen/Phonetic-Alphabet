package com.app.phoneticalphabet.ui.screens.stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StatsScreen() {
    val viewModel = hiltViewModel<StatsViewModel>()
    val state = viewModel.state.collectAsState()
    
    StatsContent(state = state.value)
}