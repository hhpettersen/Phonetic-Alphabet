package com.app.phoneticalphabet.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.phoneticalphabet.models.CompletedFlashcard
import com.app.phoneticalphabet.models.HighScore

@Database(
    entities = [HighScore::class, CompletedFlashcard::class], // Tell the database the entries will hold data of this type
    version = 4
)

abstract class Database : RoomDatabase() {
    abstract fun getDao(): DAO
}