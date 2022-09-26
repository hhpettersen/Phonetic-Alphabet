package com.app.phoneticalphabet.ui.screens.quiz

import androidx.lifecycle.ViewModel
import com.app.phoneticalphabet.extentions.generateQuestions
import com.app.phoneticalphabet.models.Question
import com.app.phoneticalphabet.models.Word
import com.app.phoneticalphabet.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class QuizViewState(
    private val questions: List<Question> = Word.alphabet.generateQuestions(),
    val question: Question = questions[0],
    val questionAnswered: Int = 0,
    val score: Int = 0,
) {
    private val numberOfQuestions: Int = 26
}

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _state = MutableStateFlow(QuizViewState())
    val state: StateFlow<QuizViewState> = _state


}