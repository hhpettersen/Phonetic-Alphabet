package com.app.phoneticalphabet.ui.screens.flashcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.phoneticalphabet.models.Word
import com.app.phoneticalphabet.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
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
    }

    fun onNextWord() {
        wordIndex += 1
        if (state.value.alphabet.lastIndex < wordIndex) {
            _state.update {
                viewModelScope.launch {
                    val date = Date().time.toString()
                    repository.insertCompletedFlashcards(date)
                }
                it.copy(
                    wordsCompleted = true,
                    completedFlashCards = it.completedFlashCards + 1,
                )
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
