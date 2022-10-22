package com.aman.cbcassignment

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.cbcassignment.adapter.CBCNewsRecyclerViewAdapter
import com.aman.cbcassignment.viewmodels.MainViewModel
import com.aman.cbcassignment.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerViewAdapter: CBCNewsRecyclerViewAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as NewsApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        initRecyclerView()

        mainViewModel.newsLiveData.observe(this) {
            if (it != null) {
                recyclerViewAdapter.setUpdatedNews(it)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }

        mainViewModel.isNetworkConnected.observe(this){
            if(it.not()){
                Toast.makeText(this@MainActivity,"Disconnected", LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,"Back Online", LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = CBCNewsRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }
}