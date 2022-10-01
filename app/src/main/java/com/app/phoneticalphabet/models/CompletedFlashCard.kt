package com.app.phoneticalphabet.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.phoneticalphabet.ui.screens.stats.FlashCardStats
import com.madrapps.plot.line.DataPoint
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "completed_flashcards")
data class CompletedFlashcard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timeStamp: String,
)

fun List<CompletedFlashcard>.flashcardStats(): List<FlashCardStats> =
    asSequence().sortedBy { Instant.parse(it.timeStamp).epochSeconds }
        .map { Instant.parse(it.timeStamp).toLocalDateTime(TimeZone.currentSystemDefault()) }
        .map {
            "${it.dayOfMonth}/${it.month.number}/${it.year}"
        }
        .groupBy { it }
        .map {
            Pair(
                first = it.value.fold(0) { t, _ -> t + 1 },
                second = it.key
            )
        }.mapIndexed { index, pair ->
            FlashCardStats(
                completions = pair.first,
                date = pair.second,
                dataPoint = DataPoint(x = index.toFloat(), y = pair.first.toFloat())
            )
        }
