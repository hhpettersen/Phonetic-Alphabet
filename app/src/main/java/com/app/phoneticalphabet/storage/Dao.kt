package com.app.phoneticalphabet.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.phoneticalphabet.models.CompletedFlashcard
import com.app.phoneticalphabet.models.QuizResult

@Dao
interface DAO {
    @Insert(entity = QuizResult::class)
    suspend fun insertQuizResult(result: QuizResult)

    @Query("SELECT * FROM quiz_results")
    suspend fun quizResultsFromCache(): List<QuizResult>

    @Insert(entity = CompletedFlashcard::class)
    suspend fun insertCompletedFlashcard(flashcard: CompletedFlashcard)

    @Query("SELECT * FROM completed_flashcards")
    suspend fun flashcardHistoryFromCache(): List<CompletedFlashcard>
}
