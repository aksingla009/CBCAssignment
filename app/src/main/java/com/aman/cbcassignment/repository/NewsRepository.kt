package com.aman.cbcassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aman.cbcassignment.db.NewsDatabase
import com.aman.cbcassignment.model.News
import com.aman.cbcassignment.retrofit.NewsAPIInterface
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsAPIInterface: NewsAPIInterface,
    private val newsDatabase: NewsDatabase
) {
    // news live Data will be observed by our view Model
    private val _news = MutableLiveData<List<News>>()
    val news : LiveData<List<News>>
    get() = _news

    // This method will be triggered by the view model once data is required
    suspend fun getProducts(){
        val result = newsAPIInterface.getNews("news")
        if (result.isSuccessful && result.body() !=null){
            newsDatabase.newsDAO().addNewsToDB(result.body()!!)
            _news.postValue(result.body())
        }
    }
}