package com.app.phoneticalphabet.ui.screens.flashcard

import androidx.lifecycle.ViewModel
import com.app.phoneticalphabet.models.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class FlashCardViewState(
    val alphabet: List<Word> = Word.alphabet.shuffled(),
    val numberCurrentWord: Int = 1,
    val currentWord: Word = alphabet[0],
    val wordsCompleted: Boolean = false,
)

@HiltViewModel
class FlashcardViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(FlashCardViewState())
    val state: StateFlow<FlashCardViewState> = _state

    private var wordIndex = 0

    fun onNextWord() {
        wordIndex += 1
        if (state.value.alphabet.lastIndex < wordIndex) {
            _state.update {
                it.copy(
                    wordsCompleted = true,
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
}
