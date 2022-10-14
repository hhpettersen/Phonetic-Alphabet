package com.app.phoneticalphabet.ui.screens.result

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.phoneticalphabet.R
import com.app.phoneticalphabet.ui.components.PhonButtonFull
import com.app.phoneticalphabet.ui.theme.MainTheme

@Composable
fun ResultContent(
    modifier: Modifier = Modifier,
    state: ResultViewState,
    onNewGame: () -> Unit,
    onCloseQuiz: () -> Unit,
    onViewStats: () -> Unit,
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.medal))

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 104.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            LottieAnimation(
                modifier = Modifier.size(200.dp),
                composition = composition
            )
            if (state.newHighScore) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center,
                    text = "New high score, congratulations!",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Text(text = "Score: ${state.score}")
            PhonButtonFull(onClick = onNewGame) {
                Text(text = "Play again")
            }
            PhonButtonFull(onClick = onViewStats) {
                Text(text = "View stats")
            }
            PhonButtonFull(onClick = onCloseQuiz) {
                Text(text = "Back home")
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
            onViewStats = {}
        )
    }
}
