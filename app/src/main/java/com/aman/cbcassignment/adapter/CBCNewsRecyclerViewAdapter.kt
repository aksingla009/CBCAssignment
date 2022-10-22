package com.aman.cbcassignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aman.cbcassignment.R
import com.aman.cbcassignment.model.News
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.news_item_layout.view.*

class CBCNewsRecyclerViewAdapter :
    RecyclerView.Adapter<CBCNewsRecyclerViewAdapter.CBCNewsItemViewHolder>() {

    private var newsListData: List<News>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CBCNewsItemViewHolder {
        val listItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout, parent, false)
        return CBCNewsItemViewHolder(listItemView)
    }

    override fun onBindViewHolder(holder: CBCNewsItemViewHolder, position: Int) {
        holder.bind(newsListData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return newsListData?.size ?: 0
    }

    fun setUpdatedNews(updatedNews: List<News>) {
        this.newsListData = updatedNews
    }

    class CBCNewsItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.imageView
        private val dateView: TextView = view.datePublishedTV
        private val titleView: TextView = view.titleTV
        private val typeView : TextView = view.typeTV

        fun bind(news: News) {
            dateView.text = news.readablePublishedAt
            typeView.text = news.type
            titleView.text = news.title

            Glide.with(imageView)
                .load(news.images.square_140)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }
    }
}

