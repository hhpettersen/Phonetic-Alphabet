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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.models.Answer
import com.app.phoneticalphabet.models.Question
import com.app.phoneticalphabet.ui.components.*
import com.app.phoneticalphabet.ui.theme.MainTheme

@Composable
fun QuizContent(
    modifier: Modifier = Modifier,
    state: QuizViewState,
    onAnswerSelected: (Answer) -> Unit,
) {
    var onGoingCount by remember { mutableStateOf(true) }

    if (onGoingCount) {
        CountDown(
            onCountFinished = { onGoingCount = false }
        )
    } else {
        Content(
            modifier = modifier,
            state = state,
            onAnswerSelected = onAnswerSelected,
        )
    }
}

@Composable
private fun Content(
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
                .padding(bottom = 104.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AnimatedExpandAndShrink(state.actionTextVisible) {
                Text(
                    text = state.actionText,
                    style = MaterialTheme.typography.headlineLarge,
                    color = state.actionTextColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(text = "Current high score: ${state.highScore}")
            Questions(
                score = state.score,
                question = state.question,
                onAnswerSelected = onAnswerSelected,
                questionsEnabled = state.questionsEnabled,
                numberCurrentQuestion = state.numberCurrentQuestion,
                numberOfQuestions = state.numberOfQuestions,
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Questions(
    score: Int,
    numberCurrentQuestion: Int,
    numberOfQuestions: Int,
    question: Question,
    onAnswerSelected: (Answer) -> Unit,
    questionsEnabled: Boolean,
) {
    Card {
        Box {
            Text(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                text = "Score: $score"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                text = "${numberCurrentQuestion}/${numberOfQuestions}"
            )
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedContent(
                    targetState = question.letter,
                    transitionSpec = {
                        Animations.slideInAndOut().using(SizeTransform(clip = false))
                    }
                ) { letter ->
                    Text(
                        text = letter,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                question.answers.forEach {
                    PhonButtonFull(
                        enabled = questionsEnabled,
                        onClick = { onAnswerSelected(it) }
                    ) {
                        Text(text = it.word)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewQuizContent() {
    MainTheme {
        Content(
            state = QuizViewState(
                questions = questions,
                questionIndex = 0,
                question = questions[0],
                questionsEnabled = true,
                numberCurrentQuestion = 3,
                score = 4,
                uiEvent = listOf(),
                highScore = 7,
                actionText = "Wrong!",
                actionTextColor = Color.Red,
                actionTextVisible = true,
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
