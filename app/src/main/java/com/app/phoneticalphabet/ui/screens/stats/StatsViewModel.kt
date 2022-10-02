package com.app.phoneticalphabet.ui.screens.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.phoneticalphabet.models.averageScore
import com.app.phoneticalphabet.models.highScore
import com.app.phoneticalphabet.models.stats
import com.app.phoneticalphabet.repository.Repository
import com.madrapps.plot.line.DataPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StatsViewState(
    val flashCardStats: List<FlashCardStat> = emptyList(),
    val totalFlashcards: Int = 0,
    val quizHighScore: Int = 0,
    val totalQuizzes: Int = 0,
    val averageQuizScore: Int = 0,
    val quizStats: List<QuizStat> = emptyList(),
)

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(StatsViewState())
    val state: StateFlow<StatsViewState> = _state

    init {
        viewModelScope.launch {
            val quizResults = repository.getQuizResults()
            val completedFlashcards = repository.getCompletedFlashcards()

            _state.update {
                it.copy(
                    flashCardStats = completedFlashcards.stats(),
                    totalFlashcards = completedFlashcards.size,
                    quizHighScore = quizResults.highScore()?.score ?: 0,
                    totalQuizzes = quizResults.size,
                    averageQuizScore = quizResults.averageScore(),
                    quizStats = quizResults.stats()
                )
            }
        }
    }
}

data class FlashCardStat(
    val completions: Int,
    val date: String,
    val dataPoint: DataPoint,
)

data class QuizStat(
    val score: Int,
    val date: String,
    val dataPoint: DataPoint,
)
