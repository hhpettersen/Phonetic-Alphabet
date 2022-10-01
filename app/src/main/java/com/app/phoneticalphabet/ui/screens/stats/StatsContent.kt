package com.app.phoneticalphabet.ui.screens.stats

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.models.*
import com.app.phoneticalphabet.ui.theme.MainTheme
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot
import kotlin.random.Random

@Composable
fun StatsContent(
    modifier: Modifier = Modifier,
    state: StatsViewState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            QuizStats(
                quizStats = state.quizStats,
                totalQuizzes = state.totalQuizzes,
                averageScore = state.averageQuizScore,
                highScore = state.quizHighScore,
            )
            FlashcardStats(
                completedFlashcards = state.flashCardStats,
                totalCompletions = state.totalFlashcards,
            )
        }
    }
}

@Composable
fun QuizStats(
    quizStats: List<QuizStat>,
    totalQuizzes: Int,
    averageScore: Int,
    highScore: Int,
) {
    var label by remember { mutableStateOf("") }

    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Quiz stats", style = MaterialTheme.typography.headlineSmall)
            Divider()
            Text(text = "High score: $highScore", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Average score: $averageScore", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Quizzes completed: $totalQuizzes", style = MaterialTheme.typography.bodyMedium)
            if (quizStats.isNotEmpty()) {
                LineGraph(
                    plot = LinePlot(
                        listOf(
                            LinePlot.Line(
                                dataPoints = quizStats.map { it.dataPoint },
                                connection = LinePlot.Connection(color = MaterialTheme.colorScheme.primary),
                                intersection = LinePlot.Intersection(color = MaterialTheme.colorScheme.secondary),
                                highlight = LinePlot.Highlight(color = MaterialTheme.colorScheme.tertiary)
                            )
                        ),
                        grid = null,
                        selection = LinePlot.Selection(
                            highlight = LinePlot.Connection(
                                MaterialTheme.colorScheme.onSurfaceVariant,
                                strokeWidth = 1.dp,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(40f, 20f))
                            )
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    onSelection = { _, points ->
                        val stats = quizStats.first { it.dataPoint == points.first() }
                        label = "${stats.date} - ${stats.score} score"
                    }
                )
            }
            Text(text = label)
        }
    }
}

@Composable
fun FlashcardStats(
    completedFlashcards: List<FlashCardStat>,
    totalCompletions: Int,
) {
    var label by remember { mutableStateOf("") }

    Card {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Flashcard stats", style = MaterialTheme.typography.headlineSmall)
            Divider()
            Text(
                text = "Flashcards completed: $totalCompletions",
                style = MaterialTheme.typography.bodyMedium
            )
            if (completedFlashcards.isNotEmpty()) {
                LineGraph(
                    plot = LinePlot(
                        listOf(
                            LinePlot.Line(
                                dataPoints = completedFlashcards.map { it.dataPoint },
                                connection = LinePlot.Connection(color = MaterialTheme.colorScheme.primary),
                                intersection = LinePlot.Intersection(color = MaterialTheme.colorScheme.secondary),
                                highlight = LinePlot.Highlight(color = MaterialTheme.colorScheme.tertiary)
                            )
                        ),
                        grid = null,
                        selection = LinePlot.Selection(
                            highlight = LinePlot.Connection(
                                MaterialTheme.colorScheme.onSurfaceVariant,
                                strokeWidth = 1.dp,
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(40f, 20f))
                            )
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    onSelection = { _, points ->
                        val stats = completedFlashcards.first { it.dataPoint == points.first() }
                        label = "${stats.date} - ${stats.completions} completions"
                    }
                )
            }
            Text(text = label)
        }
    }
}

@Preview
@Composable
fun PreviewStatsContent() {
    MainTheme {
        StatsContent(
            state = StatsViewState(
                flashCardStats = completedFlashCards.stats(),
                totalFlashcards = 6,
                quizHighScore = quizResults.highScore()?.score ?: 0,
                totalQuizzes = quizResults.size,
                averageQuizScore = quizResults.averageScore(),
                quizStats = quizResults.stats(),
            )
        )
    }
}

val quizResults = listOf(
    QuizResult(
        uid = 1,
        score = Random.nextInt(1, 25),
        timeStamp = "2022-09-01T06:58:57.417Z"
    ),
    QuizResult(
        uid = 1,
        score = Random.nextInt(1, 25),
        timeStamp = "2022-10-01T06:58:57.417Z"
    ),
    QuizResult(
        uid = 1,
        score = Random.nextInt(1, 25),
        timeStamp = "2022-11-01T06:58:57.417Z"
    ),
    QuizResult(
        uid = 1,
        score = Random.nextInt(1, 25),
        timeStamp = "2022-12-01T06:58:57.417Z"
    ),
)

private val completedFlashCards = listOf(
    CompletedFlashcard(
        id = 0,
        timeStamp = "2022-10-01T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 1,
        timeStamp = "2022-10-01T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T11:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T06:58:57.417Z"
    ),
)