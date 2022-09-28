package com.app.phoneticalphabet.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.phoneticalphabet.models.CompletedFlashcard
import com.app.phoneticalphabet.models.HighScore

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHighScore(highScore: HighScore?)

    @Query("SELECT * FROM high_score")
    suspend fun highScoreFromCache(): HighScore?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCompletedFlashcard(flashcard: CompletedFlashcard)

    @Query("SELECT * FROM completed_flashcards")
    suspend fun flashcardHistoryFromCache(): List<CompletedFlashcard>
}
