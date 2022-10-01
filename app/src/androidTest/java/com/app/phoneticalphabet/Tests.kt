package com.app.phoneticalphabet

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.app.phoneticalphabet.models.CompletedFlashcard
import com.app.phoneticalphabet.models.flashcardStats
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Tests {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.app.phoneticalphabet", appContext.packageName)
    }

    @Test
    fun shouldGetListOfFlashcardStats() {
        val stats = completedFlashCards.flashcardStats()
        Assert.assertEquals(2, stats.size)
        Assert.assertEquals(4, stats.first().completions)
    }
}

private val completedFlashCards = listOf(
    CompletedFlashcard(
        id = 0,
        timeStamp = "2022-10-01T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 1,
        timeStamp = "2022-10-01T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T11:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T06:58:57.417Z"
    ),
    CompletedFlashcard(
        id = 2,
        timeStamp = "2021-09-11T06:58:57.417Z"
    ),
)