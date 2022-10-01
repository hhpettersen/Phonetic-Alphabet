package com.app.phoneticalphabet.ui.screens.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.phoneticalphabet.models.CompletedFlashcard
import com.app.phoneticalphabet.models.flashcardStats
import com.app.phoneticalphabet.ui.theme.MainTheme
import com.madrapps.plot.line.LineGraph
import com.madrapps.plot.line.LinePlot

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
                .padding(bottom = 128.dp)
        ) {
            FlashcardStats(
                completedFlashcards = state.flashCardStats,
                totalCompletions = state.totalCompletions,
            )
        }
    }
}

@Composable
fun FlashcardStats(
    completedFlashcards: List<FlashCardStats>,
    totalCompletions: Int,
) {
    var label by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total completions: $totalCompletions")
            Text(text = label)
        }
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
    }
}

@Preview
@Composable
fun PreviewStatsContent() {
    MainTheme {
        StatsContent(
            state = StatsViewState(
                flashCardStats = completedFlashCards.flashcardStats(),
                totalCompletions = 6,
                highScore = 15
            )
        )
    }
}

val completedFlashCards = listOf(
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