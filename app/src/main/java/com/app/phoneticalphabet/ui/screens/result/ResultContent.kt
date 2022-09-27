package com.app.phoneticalphabet.ui.screens.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    state: ResultViewState,
    onNewGame: () -> Unit,
    onCloseQuiz: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Column {
                Text(text = "${state.tierText} TIER")
                Text(text = "Score: ${state.score}")
                Row {
                   Button(onClick = { onNewGame() }) {
                       Text(text = "Play again")
                   }
                   Button(onClick = { onCloseQuiz() }) {
                       Text(text = "Back home")
                   }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewResultContent() {
    ResultContent(
        state = ResultViewState(
            score = 2,
            medalTint = 0,
            tierText = "BRONZE"
        ),
        onNewGame = {},
        onCloseQuiz = {},
    )
}
