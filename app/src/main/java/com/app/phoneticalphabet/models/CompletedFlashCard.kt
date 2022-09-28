package com.app.phoneticalphabet.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_flashcards")
data class CompletedFlashcard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeStamp: String,
)
