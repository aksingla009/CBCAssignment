package com.aman.cbcassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aman.cbcassignment.repository.NewsRepository
import javax.inject.Inject

// Since ViewModel needed repository's instance while instantiating itself
// So we had to create ViewModelFactory to achieve that
class MainViewModelFactory @Inject constructor(
    private val repository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}