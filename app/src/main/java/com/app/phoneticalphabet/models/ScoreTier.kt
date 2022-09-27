package com.app.phoneticalphabet.models

enum class ScoreTier {
    BRONZE,
    SILVER,
    GOLD;

    companion object {
        fun getTier(score: Int): ScoreTier =
            when {
                score < 15 -> BRONZE
                score < 23 -> SILVER
                else -> GOLD
            }

        fun ScoreTier.medalTint(): Int =
            when (this) {
                BRONZE -> 0
                SILVER -> 0
                GOLD -> 0
            }

        fun ScoreTier.tierText(): String =
            when (this) {
                BRONZE -> "BRONZE"
                SILVER -> "SILVER"
                GOLD -> "GOLD"
            }
    }
}