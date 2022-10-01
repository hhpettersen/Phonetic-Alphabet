package com.app.phoneticalphabet.repository

import androidx.annotation.WorkerThread
import com.app.phoneticalphabet.models.CompletedFlashcard
import com.app.phoneticalphabet.models.QuizResult
import com.app.phoneticalphabet.storage.DAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val DAO: DAO
) {

    @WorkerThread
    suspend fun insertQuizResult(result: QuizResult) = withContext(Dispatchers.IO) {
        DAO.insertQuizResult(result)
    }

    suspend fun getQuizResults() = DAO.quizResultsFromCache()

    @WorkerThread
    suspend fun insertCompletedFlashcards(timeStamp: String) = withContext(Dispatchers.IO) {
        DAO.insertCompletedFlashcard(CompletedFlashcard(timeStamp = timeStamp))
    }

    suspend fun getCompletedFlashcards() = DAO.flashcardHistoryFromCache()
}
