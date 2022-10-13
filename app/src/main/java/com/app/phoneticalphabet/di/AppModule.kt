package com.app.phoneticalphabet.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.app.phoneticalphabet.storage.Database
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //Room DI
    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        Database::class.java,
        "phonetic_db"
    ).fallbackToDestructiveMigration()
        .build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideYourDao(db: Database) =
        db.getDao() // The reason we can implement a Dao for the database

    @Provides
    @Singleton
    fun provideFirebaseAnalytics() = Firebase.analytics
}

// Unused, crashes for some reason when field injecting in MainActivity
@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferences_name", Context.MODE_PRIVATE)
    }
}
