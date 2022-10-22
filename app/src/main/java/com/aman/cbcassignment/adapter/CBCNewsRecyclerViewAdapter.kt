package com.aman.cbcassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aman.cbcassignment.R

class CBCNewsRecyclerViewAdapter : RecyclerView.Adapter<CBCNewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CBCNewsItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.news_item_layout,parent,false)
        return CBCNewsItemViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: CBCNewsItemViewHolder, position: Int) {
        holder.titleTV.text = "AMAN"
    }

    override fun getItemCount(): Int {
        return 5
    }
}

class CBCNewsItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    val titleTV = view.findViewById<TextView>(R.id.titleTV)
}