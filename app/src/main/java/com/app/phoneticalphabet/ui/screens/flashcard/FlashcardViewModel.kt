package com.app.phoneticalphabet.ui.screens.flashcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.phoneticalphabet.firebase.FirebaseEvent
import com.app.phoneticalphabet.models.Word
import com.app.phoneticalphabet.repository.Repository
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

data class FlashCardViewState(
    val alphabet: List<Word> = Word.alphabet.shuffled(),
    val numberCurrentWord: Int = 1,
    val currentWord: Word = alphabet[0],
    val wordsCompleted: Boolean = false,
    val completedFlashCards: Int = 0,
)

@HiltViewModel
class FlashcardViewModel @Inject constructor(
    private val repository: Repository,
    private val analytics: FirebaseAnalytics,
) : ViewModel() {
    private val _state = MutableStateFlow(FlashCardViewState())
    val state: StateFlow<FlashCardViewState> = _state

    private var wordIndex = 0

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    completedFlashCards = repository.getCompletedFlashcards().size
                )
            }
        }

        // Log firebase-event
        analytics.logEvent(FirebaseEvent.FLASHCARD_STARTED) {
            param("completed_flashcards", state.value.completedFlashCards.toString())
        }
    }

    fun onNextWord() {
        wordIndex += 1
        if (state.value.alphabet.lastIndex < wordIndex) {
            _state.update {
                viewModelScope.launch {
                    repository.insertCompletedFlashcards(Clock.System.now().toString())
                }
                it.copy(
                    wordsCompleted = true,
                    completedFlashCards = it.completedFlashCards + 1,
                )
            }

            // Log firebase-event
            analytics.logEvent(FirebaseEvent.FLASHCARD_FINISHED) {
                param("completed_flashcards", state.value.completedFlashCards.toString())
            }
        } else {
            _state.update {
                it.copy(
                    numberCurrentWord = it.numberCurrentWord + 1,
                    currentWord = it.alphabet[wordIndex]
                )
            }
        }
    }

    fun onNewRound() {
        wordIndex = 0
        val alphabetShuffled = state.value.alphabet.shuffled()
        _state.update {
            it.copy(
                alphabet = alphabetShuffled,
                wordsCompleted = false,
                numberCurrentWord = 0,
                currentWord = alphabetShuffled[wordIndex],
            )
        }
    }
}
