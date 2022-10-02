package com.app.phoneticalphabet.ui.screens.quiz

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.phoneticalphabet.extentions.generateQuestions
import com.app.phoneticalphabet.models.*
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
    data class EndQuiz(val finalScore: Int, val newHighScore: Boolean) : UiEvent()
}

data class QuizViewState(
    val countingDown: Boolean = true,
    val countDown: Int = 0,
    val questions: List<Question> = Word.alphabet.generateQuestions(),
    val questionIndex: Int = 0,
    val question: Question = questions[questionIndex],
    val questionsEnabled: Boolean = true,
    val numberCurrentQuestion: Int = 1,
    val score: Int = 0,
    val uiEvent: List<UiEvent> = emptyList(),
    val highScore: Int = 0,
    val actionText: String = "",
    val actionTextColor: Color = Color.Green,
    val actionTextVisible: Boolean = false,
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
                    highScore = repository.getQuizResults().highScore()?.score ?: 0
                )
            }
        }
        startCountDown()
    }

    private fun startCountDown() {
        val countDownTimer = 3
        viewModelScope.launch {
            repeat(countDownTimer + 1) { count ->
                _state.update {
                    it.copy(
                        countDown = (countDownTimer - count),
                        countingDown = count < countDownTimer
                    )
                }
                delay(1000)
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
                    actionText = if (answer.correct) "Correct!" else "Wrong!",
                    actionTextColor = if (answer.correct) Color.Green else Color.Red,
                    actionTextVisible = true,
                )
            }
            delay(1500)
            _state.update {
                it.copy(
                    actionTextVisible = false,
                )
            }
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
            repository.insertQuizResult(
                QuizResult(score = state.value.score)
            )
        }
        updateUiEvent(
            UiEvent.EndQuiz(
                finalScore = state.value.score,
                newHighScore = state.value.score > state.value.highScore
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
