package com.app.phoneticalphabet.models

enum class ScoreTier(requiredScored: Int) {
    BRONZE(0),
    SILVER(15),
    GOLD(23);

    companion object {
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