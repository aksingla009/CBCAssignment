package com.aman.cbcassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.cbcassignment.model.News
import com.aman.cbcassignment.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {

    // Created newsLiveData which will be observed by Activity
    // It's value is assigned from repository's Live data
    val newsLiveData : LiveData<List<News>>
    get() = repository.news

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getProducts()
        }
    }


}