package com.app.phoneticalphabet.ui.screens.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.phoneticalphabet.models.Answer
import com.app.phoneticalphabet.models.Question

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    onEndGame: () -> Unit,
) {
    val viewModel = hiltViewModel<QuizViewModel>()

    val state = viewModel.state.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Current question: ${state.value.numberCurrentQuestion}/${state.value.numberOfQuestions}")
            Text(text = "Score: ${state.value.score}")
            Questions(
                question = state.value.question,
                onQuestionClicked = { viewModel.onAnswerSelected(it) },
                questionsEnabled = state.value.questionsEnabled,
            )
        }
    }
}

@Composable
fun Questions(
    question: Question,
    onQuestionClicked: (Answer) -> Unit,
    questionsEnabled: Boolean,
) {
    question.answers.forEach {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            onClick = { onQuestionClicked(it) },
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
