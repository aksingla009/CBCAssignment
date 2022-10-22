package com.aman.cbcassignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aman.cbcassignment.model.News

@Database(entities = [News::class], version = 1)
@TypeConverters(ImageTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDAO(): NewsDAO
}