package com.app.phoneticalphabet.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    var score: Int = 0
)
