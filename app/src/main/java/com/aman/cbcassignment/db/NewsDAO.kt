package com.aman.cbcassignment.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aman.cbcassignment.model.News

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewsToDB(news: List<News>)

    @Query("SELECT * FROM NEWS")
    suspend fun getNewsFromDB(): List<News>
}