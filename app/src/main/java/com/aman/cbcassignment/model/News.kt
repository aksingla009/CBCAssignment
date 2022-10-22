package com.aman.cbcassignment.model

data class News(
    val id: Int,
    val images: Images,
    val readablePublishedAt: String,
    val title: String,
    val type: String
)