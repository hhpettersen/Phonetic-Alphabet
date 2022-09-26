package com.app.phoneticalphabet.models

data class Question(
    val answers: List<Answer>,
    val letter: String
)
