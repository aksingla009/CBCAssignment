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

    // Network Availability live Data will be observed by our view Model
    private val _isNetworkConnected = MutableLiveData<Boolean>()
    val isNetworkConnected: LiveData<Boolean>
        get() = _isNetworkConnected

    // This method will be triggered by the view model once data is required
    suspend fun getProductsFromAPI() {
        //Before Hitting the API First check if network is available or not
        // And communicate to the UI as well
        if (NetworkUtils.isInternetAvailable(context)) {
            _isNetworkConnected.postValue(true)
            val result = newsAPIInterface.getNews("news")
            if (result.isSuccessful && result.body() != null) {
                //Save Data to Database once its available from API
                newsDatabase.newsDAO().addNewsToDB(result.body()!!)

                //To be observed in the ViewModel
                _news.postValue(result.body())
            }
        } else {
            // If network is not connected then update UI accordingly
            // And fetch Data from DB saved earlier
            _isNetworkConnected.postValue(false)
            val news = newsDatabase.newsDAO().getNewsFromDB()
            _news.postValue(news)
        }


    }
}