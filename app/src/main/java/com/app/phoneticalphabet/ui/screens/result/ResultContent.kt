package com.app.phoneticalphabet.ui.screens.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.ui.components.StandardButton
import com.app.phoneticalphabet.ui.theme.MainTheme

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
                .padding(bottom = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (state.newHighScore) {
                Text(
                    text = "New high score, congratulations!",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Text(text = "Score: ${state.score}")
            StandardButton(text = "Play again") {
                onNewGame()
            }
            StandardButton(text = "Back home") {
                onCloseQuiz()
            }
        }
    }
}

@Preview
@Composable
fun PreviewResultContent() {
    MainTheme {
        ResultContent(
            state = ResultViewState(
                score = 2,
                medalTint = 0,
                tierText = "BRONZE",
                newHighScore = true,
            ),
            onNewGame = {},
            onCloseQuiz = {},
        )
    }
}
