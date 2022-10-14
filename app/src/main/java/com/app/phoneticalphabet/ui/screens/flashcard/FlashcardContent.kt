package com.app.phoneticalphabet.ui.screens.flashcard

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.app.phoneticalphabet.R
import com.app.phoneticalphabet.models.Word
import com.app.phoneticalphabet.ui.components.Animations
import com.app.phoneticalphabet.ui.components.CountDown
import com.app.phoneticalphabet.ui.components.PhonButtonFull
import com.app.phoneticalphabet.ui.extensions.blurEffect
import com.app.phoneticalphabet.ui.theme.MainTheme

@Composable
fun FlashcardContent(
    modifier: Modifier = Modifier,
    state: FlashCardViewState,
    onNextWordClicked: () -> Unit,
    onNewRound: () -> Unit,
    onViewStats: () -> Unit,
    onEndFlashcards: () -> Unit,
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
            onNextWordClicked = onNextWordClicked,
            onNewRound = onNewRound,
            onViewStats = onViewStats,
            onEndFlashcards = onEndFlashcards,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: FlashCardViewState,
    onNextWordClicked: () -> Unit,
    onNewRound: () -> Unit,
    onViewStats: () -> Unit,
    onEndFlashcards: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 104.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (state.wordsCompleted) {
                val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.medal))

                LottieAnimation(
                    modifier = Modifier.size(200.dp),
                    composition = composition
                )
            }
            Text(text = "Completed flashcards: ${state.completedFlashCards}")
            if (state.wordsCompleted) {
                CompletedContent(
                    onNewRound = onNewRound,
                    onViewStats = onViewStats,
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
    onViewStats: () -> Unit,
    onEndFlashcards: () -> Unit,
) {
    PhonButtonFull(onClick = onNewRound) {
        Text(text = "New round")
    }
    PhonButtonFull(onClick = onViewStats) {
        Text(text = "View stats")
    }
    PhonButtonFull(onClick = onEndFlashcards) {
        Text(text = "Back to dashboard")
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Flashcard(
    state: FlashCardViewState,
    onNextWordClicked: () -> Unit,
) {
    val wordVisible = remember { mutableStateOf(false) }
    val buttonText = if (wordVisible.value) "Next" else "Reveal word"

    Card {
        Box {
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                text = "${state.numberCurrentWord}/${state.alphabet.size}"
            )
            Column(
                modifier = Modifier.padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AnimatedContent(
                    targetState = state.currentWord.letter,
                    transitionSpec = {
                        Animations.slideInAndOut().using(SizeTransform(clip = false))
                    }
                ) { letter ->
                    Text(
                        text = letter,
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
                Text(
                    modifier = Modifier.blurEffect(wordVisible.value),
                    text = state.currentWord.word
                )
                PhonButtonFull(
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
        }
    }
}

@Preview
@Composable
fun PreviewFlashcardContent() {
    MainTheme {
        Content(
            state = FlashCardViewState(
                alphabet = Word.alphabet,
                numberCurrentWord = 1,
                currentWord = Word.alphabet[5],
                wordsCompleted = false,
                completedFlashCards = 4
            ),
            onNextWordClicked = {},
            onNewRound = {},
            onViewStats = {},
            onEndFlashcards = {}
        )
    }
}

@Preview
@Composable
fun PreviewFlashcardContentFinished() {
    MainTheme {
        Content(
            state = FlashCardViewState(
                alphabet = Word.alphabet,
                numberCurrentWord = 1,
                currentWord = Word.alphabet[5],
                wordsCompleted = true,
                completedFlashCards = 4
            ),
            onNextWordClicked = {},
            onNewRound = {},
            onViewStats = {},
            onEndFlashcards = {}
        )
    }
}
