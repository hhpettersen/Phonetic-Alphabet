package com.app.phoneticalphabet.extentions

import com.app.phoneticalphabet.models.Answer
import com.app.phoneticalphabet.models.Question
import com.app.phoneticalphabet.models.Word
import java.util.*

fun List<Word>.generateQuestions(): List<Question> =
    map {
        Question(
            answers = it.generateAnswers(Word.wrongWords),
            letter = it.letter
        )
    }

private fun Word.generateAnswers(wrongWords: List<String>) =
    wrongWords.getWordsStartingWith(letter)
        .getWrongAnswers()
        .plus(getCorrectAnswer())
        .shuffled()

private fun List<String>.getWordsStartingWith(letter: String): List<String> =
    shuffled()
        .take(3)
        .filter {
            it.lowercase(Locale.getDefault())
                .startsWith(
                    letter.lowercase(Locale.ROOT)
                )
        }

private fun List<String>.getWrongAnswers(): List<Answer> =
    map {
        Answer(
            word = it,
            correct = false
        )
    }

private fun Word.getCorrectAnswer(): Answer =
    Answer(
        word = word,
        correct = true
    )
