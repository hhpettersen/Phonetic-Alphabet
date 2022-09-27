package com.app.phoneticalphabet.ui.screens.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.phoneticalphabet.extentions.generateQuestions
import com.app.phoneticalphabet.models.Answer
import com.app.phoneticalphabet.models.HighScore
import com.app.phoneticalphabet.models.Question
import com.app.phoneticalphabet.models.Word
import com.app.phoneticalphabet.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

sealed class UiEvent(val id: Long = UUID.randomUUID().mostSignificantBits) {
    data class EndQuiz(val finalScore: Int) : UiEvent()
}

data class QuizViewState(
    val questions: List<Question> = Word.alphabet.generateQuestions(),
    val questionIndex: Int = 0,
    val question: Question = questions[questionIndex],
    val questionsEnabled: Boolean = true,
    val numberCurrentQuestion: Int = 1,
    val score: Int = 0,
    val uiEvent: List<UiEvent> = emptyList(),
    val highScore: Int = 0,
) {
    val numberOfQuestions: Int = questions.size
}

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _state = MutableStateFlow(QuizViewState())
    val state: StateFlow<QuizViewState> = _state

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    highScore = repository.getHighScore().score
                )
            }
        }
    }

    fun onAnswerSelected(answer: Answer) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    score = if (answer.correct) it.score + 1 else it.score,
                    questionIndex = it.questionIndex + 1,
                    questionsEnabled = false,
                )
            }
            delay(1500)
            if (state.value.numberOfQuestions == state.value.questionIndex) {
                endQuiz()
            } else {
                _state.update {
                    it.copy(
                        numberCurrentQuestion = it.numberCurrentQuestion + 1,
                        question = it.questions[it.questionIndex],
                        questionsEnabled = true,
                    )
                }
            }
        }
    }

    private fun endQuiz() {
        viewModelScope.launch {
            repository.insertHighScore(HighScore(score = state.value.score))
        }
        updateUiEvent(
            UiEvent.EndQuiz(
                finalScore = state.value.score
            )
        )
    }

    private fun updateUiEvent(uiEvent: UiEvent) {
        _state.update {
            state.value.copy(
                uiEvent = state.value.uiEvent.toMutableList() + uiEvent,
            )
        }
    }

    fun onUiEventHandled(id: Long) =
        _state.update {
            val uiEvents = state.value.uiEvent
            state.value.copy(
                uiEvent = uiEvents.toMutableList() - uiEvents.first { id == it.id },
            )
        }
}
