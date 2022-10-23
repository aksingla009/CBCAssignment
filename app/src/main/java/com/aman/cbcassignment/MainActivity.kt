package com.aman.cbcassignment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aman.cbcassignment.adapter.CBCNewsRecyclerViewAdapter
import com.aman.cbcassignment.model.News
import com.aman.cbcassignment.viewmodels.MainViewModel
import com.aman.cbcassignment.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerViewAdapter: CBCNewsRecyclerViewAdapter

    private var filteredTypesList: List<String> = mutableListOf()
    private var newsList: List<News> = mutableListOf()

    private lateinit var filterSpinnerAdapter: ArrayAdapter<String>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as NewsApplication).applicationComponent.inject(this)

        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        initRecyclerView()

        mainViewModel.newsLiveData.observe(this) { newsList ->
            if (newsList != null) {
                this.newsList = newsList
                //create list of String which will have only Unique Type
                filteredTypesList = mainViewModel.getFilteredListOfTypes()

                filterSpinnerAdapter.clear()
                filterSpinnerAdapter.addAll(filteredTypesList)

                setUpdatedListOfNews(newsList)
            }
        }

        mainViewModel.isNetworkConnected.observe(this) {
            if (it.not()) {
                Toast.makeText(this@MainActivity, "Disconnected", LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "Back Online", LENGTH_LONG).show()
            }
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = CBCNewsRecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val item = menu?.findItem(R.id.menu_filter_type)
        val spinner = item?.actionView as AppCompatSpinner

        filterSpinnerAdapter = ArrayAdapter(
            this@MainActivity,
            R.layout.layout_spinner_item,
            filteredTypesList
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            adapter.setNotifyOnChange(true)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    setUpdatedListOfNews(newsList.filter {
                        it.type == filteredTypesList[position]
                    })
                } else {
                    setUpdatedListOfNews(newsList)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpdatedListOfNews(newsList: List<News>) {
        recyclerViewAdapter.setUpdatedNews(newsList)
        recyclerViewAdapter.notifyDataSetChanged()
    }
}