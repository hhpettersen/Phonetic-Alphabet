package com.app.phoneticalphabet.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.phoneticalphabet.ui.screens.stats.QuizStat
import com.madrapps.plot.line.DataPoint
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToInt

@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo var score: Int = 0,
    @ColumnInfo val timeStamp: String = Clock.System.now().toString(),
)

fun List<QuizResult>.stats(): List<QuizStat> =
    sortedBy { Instant.parse(it.timeStamp).epochSeconds }
        .mapIndexed { index, highScore ->
            val instant = Instant.parse(highScore.timeStamp).toLocalDateTime(TimeZone.currentSystemDefault())
            QuizStat(
                score = highScore.score,
                date = "${instant.dayOfMonth}/${instant.monthNumber}/${instant.year}",
                dataPoint = DataPoint(x = index.toFloat(), highScore.score.toFloat())
            )
        }

fun List<QuizResult>.averageScore(): Int = if (this.isNotEmpty()) map { it.score }.average().roundToInt() else 0

fun List<QuizResult>.highScore(): QuizResult? = maxByOrNull { it.score }