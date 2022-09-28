package com.app.phoneticalphabet.ui.screens.flashcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.phoneticalphabet.models.Word

@Composable
fun FlashcardContent(
    modifier: Modifier = Modifier,
    state: FlashCardViewState,
    onNextWordClicked: () -> Unit,
    onNewRound: () -> Unit,
    onEndFlashcards: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (state.wordsCompleted) {
                CompletedContent(
                    onNewRound = onNewRound,
                    onEndFlashcards = onEndFlashcards,
                )
            } else {
                Flashcard(
                    state = state,
                    onNextWordClicked,
                )
            }
        }
    }
}

@Composable
fun CompletedContent(
    onNewRound: () -> Unit,
    onEndFlashcards: () -> Unit,
) {
    Button(onClick = onNewRound) {
        Text(text = "New round")
    }
    Button(onClick = onEndFlashcards) {
        Text(text = "Back to dashboard")
    }
}

@Composable
fun Flashcard(
    state: FlashCardViewState,
    onNextWordClicked: () -> Unit,
) {
    val wordVisible = remember { mutableStateOf(false) }

    val buttonText = if (wordVisible.value) "Next" else "Reveal word"
    val wordText =
        if (wordVisible.value) state.currentWord.word else state.currentWord.word.map { "_" }
            .joinToString("")

    Text(text = "${state.numberCurrentWord}/${state.alphabet.size}")
    Text(text = state.currentWord.letter)
    Text(text = wordText)
    Button(
        onClick = {
            if (wordVisible.value) {
                wordVisible.value = false
                onNextWordClicked()
            } else {
                wordVisible.value = true
            }
        }
    ) {
        Text(text = buttonText)
    }
}

@Preview
@Composable
fun PreviewFlashcardContent() {
    FlashcardContent(
        state = FlashCardViewState(
            alphabet = Word.alphabet,
            numberCurrentWord = 1,
            currentWord = Word.alphabet[5],
            wordsCompleted = false,
        ),
        onNextWordClicked = {},
        onNewRound = {},
        onEndFlashcards = {}
    )
}
