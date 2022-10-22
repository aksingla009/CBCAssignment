package com.aman.cbcassignment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aman.cbcassignment.adapter.CBCNewsRecyclerViewAdapter
import com.aman.cbcassignment.viewmodels.MainViewModel
import com.aman.cbcassignment.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as NewsApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setBackgroundColor(Color.MAGENTA)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CBCNewsRecyclerViewAdapter()

        mainViewModel.newsLiveData.observe(this) {
            val newsList = it?.listIterator()
            if (newsList != null) {
                while (newsList.hasNext()) {
                    val newsItem = newsList.next()
                    Log.e("AMAND", newsItem.title)
                    Log.e("AMANDEEP", newsItem.images.square_140)
                }
            }
        }
    }
}