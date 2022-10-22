package com.aman.cbcassignment.retrofit

import com.aman.cbcassignment.model.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIInterface {
    // This method will be called from our repository
    @GET("items")
    suspend fun getNews(
        @Query("lineupSlug") lineupSlug:String
    ): Response<List<News>>

    @GET("items")
    suspend fun getNewsWithType(
        @Query("type") type:String
    ): Response<List<News>>

}