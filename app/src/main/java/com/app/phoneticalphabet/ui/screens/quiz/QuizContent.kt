package com.app.phoneticalphabet.ui.screens.quiz

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.models.Answer
import com.app.phoneticalphabet.models.Question
import com.app.phoneticalphabet.ui.components.AnimatedExpandAndShrink
import com.app.phoneticalphabet.ui.components.Animations
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
        if (state.countingDown) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = state.countDown.toString(),
                style = MaterialTheme.typography.headlineLarge
            )
        } else {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 104.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AnimatedExpandAndShrink(state.actionTextVisible) {
                    Text(
                        text = state.actionText,
                        style = MaterialTheme.typography.headlineLarge,
                        color = state.actionTextColor
                    )
                }
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
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Questions(
    question: Question,
    onAnswerSelected: (Answer) -> Unit,
    questionsEnabled: Boolean,
) {

    Card {
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedContent(
                targetState = question.letter,
                transitionSpec = { Animations.slideInAndOut() }
            ) { letter ->
                Text(text = letter, style = MaterialTheme.typography.titleLarge)
            }
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
