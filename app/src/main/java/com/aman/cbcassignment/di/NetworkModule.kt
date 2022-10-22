package com.aman.cbcassignment.di

import com.aman.cbcassignment.retrofit.NewsAPIInterface
import com.aman.cbcassignment.utils.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Since retrofit uses Builder pattern to create its object
// We have to use Module for Dependency Injection
@Module
class NetworkModule {
    // We would want that There should be only one instance of Retrofit
    // So marked it as Singleton
    @Singleton
    @Provides
    fun providesRetroFit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsAPIInterface(retrofit: Retrofit): NewsAPIInterface {
        return retrofit.create(NewsAPIInterface::class.java)
    }
}