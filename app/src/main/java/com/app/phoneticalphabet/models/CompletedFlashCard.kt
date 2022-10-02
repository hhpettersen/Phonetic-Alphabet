package com.app.phoneticalphabet.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.phoneticalphabet.ui.screens.stats.FlashCardStat
import com.madrapps.plot.line.DataPoint
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity(tableName = "completed_flashcards")
data class CompletedFlashcard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val timeStamp: String = Clock.System.now().toString(),
)

fun List<CompletedFlashcard>.stats(): List<FlashCardStat> =
    asSequence().sortedBy { Instant.parse(it.timeStamp).epochSeconds }
        .map { Instant.parse(it.timeStamp).toLocalDateTime(TimeZone.currentSystemDefault()) }
        .map {
            "${it.dayOfMonth}/${it.monthNumber}/${it.year}"
        }
        .groupBy { it }
        .map {
            Pair(
                first = it.value.fold(0) { t, _ -> t + 1 },
                second = it.key
            )
        }.mapIndexed { index, pair ->
            FlashCardStat(
                completions = pair.first,
                date = pair.second,
                dataPoint = DataPoint(x = index.toFloat(), y = pair.first.toFloat())
            )
        }
