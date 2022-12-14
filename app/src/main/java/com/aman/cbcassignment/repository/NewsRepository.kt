package com.aman.cbcassignment.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aman.cbcassignment.db.NewsDatabase
import com.aman.cbcassignment.model.News
import com.aman.cbcassignment.retrofit.NewsAPIInterface
import com.aman.cbcassignment.utils.NetworkUtils
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsAPIInterface: NewsAPIInterface,
    private val newsDatabase: NewsDatabase,
    private val context: Context
) {
    // news live Data will be observed by our view Model
    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>>
        get() = _news

    // This method will be triggered by the view model once data is required
    suspend fun getNews() {
        //Before Hitting the API First check if network is available or not
        if (NetworkUtils.isInternetAvailable(context)) {
            val result = newsAPIInterface.getNews("news")
            if (result.isSuccessful && result.body() != null) {
                //Save Data to Database once its available from API
                // Delete previous entries from DB if API is available
                newsDatabase.newsDAO().deleteFromDB()
                newsDatabase.newsDAO().addNewsToDB(result.body()!!)

                //To be observed in the ViewModel
                _news.postValue(result.body())
            }
        } else {
            // If network is not connected then update UI accordingly
            // And fetch Data from DB saved earlier
            val news = newsDatabase.newsDAO().getNewsFromDB()
            _news.postValue(news)
        }
    }
}