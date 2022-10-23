package com.aman.cbcassignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.cbcassignment.model.News
import com.aman.cbcassignment.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    // Created newsLiveData which will be observed by Activity
    // It's value is assigned from repository's Live data
    val newsLiveData: LiveData<List<News>>
        get() = repository.news

    init {
        viewModelScope.launch(Dispatchers.IO) {
            // viewModel will request to get News from Repository
            // View Model need not worry about the Connection Status and underlying implementation of repo

            repository.getNews()
        }
    }

    fun getFilteredListOfTypes(): List<String> {
        val spinnerList = mutableListOf<String>()
        spinnerList.add(0, "SELECT")

        val filteredList = newsLiveData.value?.distinctBy {
            it.type
        }?.map { filteredType ->
            filteredType.type
        }
        filteredList?.let {
            spinnerList.addAll(it)
        }
        return spinnerList
    }


}