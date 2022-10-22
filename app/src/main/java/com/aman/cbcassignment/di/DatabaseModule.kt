package com.aman.cbcassignment.di

import android.content.Context
import androidx.room.Room
import com.aman.cbcassignment.db.NewsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun providesNewsDataBase(context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "newsDB"
        ).build()
    }
}