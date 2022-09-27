package com.app.phoneticalphabet.ui.screens.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.models.Answer
import com.app.phoneticalphabet.models.Question

@Composable
fun QuizContent(
    modifier: Modifier = Modifier,
    state: QuizViewState,
    onAnswerSelected: (Answer) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Current high score: ${state.highScore}")
            Text(text = "Current question: ${state.numberCurrentQuestion}/${state.numberOfQuestions}")
            Text(text = "Score: ${state.score}")
            Questions(
                question = state.question,
                onAnswerSelected = onAnswerSelected,
                questionsEnabled = state.questionsEnabled,
            )
        }
    }
}

@Composable
fun Questions(
    question: Question,
    onAnswerSelected: (Answer) -> Unit,
    questionsEnabled: Boolean,
) {
    question.answers.forEach {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onClick = { onAnswerSelected(it) },
            enabled = questionsEnabled
        ) {
            Text(text = it.word)
        }
    }
}

@Preview
@Composable
fun PreviewQuizScreen() {
    QuizScreen(
        onEndGame = {}
    )
}
