package com.app.phoneticalphabet.ui.screens.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.models.Answer
import com.app.phoneticalphabet.models.Question
import com.app.phoneticalphabet.ui.components.StandardButton
import com.app.phoneticalphabet.ui.theme.MainTheme

@Composable
fun QuizContent(
    modifier: Modifier = Modifier,
    state: QuizViewState,
    onAnswerSelected: (Answer) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = state.actionText, style = MaterialTheme.typography.titleLarge, color = state.actionTextColor)
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
    Column {
        question.answers.forEach {
            StandardButton(
                text = it.word,
                enabled = questionsEnabled
            ) {
                onAnswerSelected(it)
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuizContent() {
    MainTheme {
        QuizContent(
            state = QuizViewState(
                questions = questions,
                questionIndex = 0,
                question = questions[0],
                questionsEnabled = true,
                numberCurrentQuestion = 0,
                score = 4,
                uiEvent = listOf(),
                highScore = 7,
                actionText = "Wrong!",
                actionTextColor = Color.Red,
            ), onAnswerSelected = {}
        )
    }
}

private val questions = listOf(
    Question(
        answers = listOf(
            Answer(
                word = "Alpha",
                correct = false,
            ),
            Answer(
                word = "Alpha",
                correct = false,
            ),
            Answer(
                word = "Alpha",
                correct = false,
            ),
            Answer(
                word = "Alpha",
                correct = false,
            ),
        ),
        letter = "A"
    )
)
