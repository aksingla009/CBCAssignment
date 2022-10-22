package com.aman.cbcassignment.db

import androidx.room.*
import com.aman.cbcassignment.model.News

@Dao
interface NewsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewsToDB(news: List<News>)

    @Query("SELECT * FROM NEWS")
    suspend fun getNewsFromDB(): List<News>

    @Query("DELETE FROM NEWS")
    suspend fun deleteFromDB()
}