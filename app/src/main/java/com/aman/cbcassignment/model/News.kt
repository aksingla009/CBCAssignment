package com.aman.cbcassignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey(autoGenerate = true)
    val newsID : Int,
    val id: Int,
    val images: Images,
    val readablePublishedAt: String,
    val title: String,
    val type: String
)