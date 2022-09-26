package com.app.phoneticalphabet.ui.screens.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
            Questions(
                question = state.value.question,
                onQuestionClicked = { onEndGame() }
            )
        }
    }
}

@Composable
fun Questions(
    question: Question,
    onQuestionClicked: (Answer) -> Unit,
) {
    question.answers.forEach {
        Button(onClick = { onQuestionClicked(it) }) {
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
