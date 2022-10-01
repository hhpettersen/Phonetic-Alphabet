package com.app.phoneticalphabet.ui.screens.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.phoneticalphabet.models.flashcardStats
import com.app.phoneticalphabet.repository.Repository
import com.madrapps.plot.line.DataPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StatsViewState(
    val flashCardStats: List<FlashCardStats> = emptyList(),
    val totalCompletions: Int = 0,
    val highScore: Int = 0
)

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(StatsViewState())
    val state: StateFlow<StatsViewState> = _state

    init {
        viewModelScope.launch {



            _state.update {
                it.copy(
                    flashCardStats = completedFlashCards.flashcardStats(),
//                    flashCardStats = repository.getCompletedFlashcards().flashcardStats(),
                    totalCompletions = completedFlashCards.size,
                    highScore = repository.getHighScore()?.score ?: 0,
                )
            }
        }
    }
}

data class FlashCardStats(
    val completions: Int,
    val date: String,
    val dataPoint: DataPoint,
)
