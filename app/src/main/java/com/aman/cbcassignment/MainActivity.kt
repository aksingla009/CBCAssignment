package com.aman.cbcassignment

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aman.cbcassignment.adapter.CBCNewsRecyclerViewAdapter
import com.aman.cbcassignment.viewmodels.MainViewModel
import com.aman.cbcassignment.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerViewAdapter: CBCNewsRecyclerViewAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as NewsApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        initRecyclerView()

        mainViewModel.newsLiveData.observe(this) {
            if (it != null) {
                recyclerViewAdapter.setUpdatedNews(it)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = CBCNewsRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }
}