package com.example.api_integration_example

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.api_integration_example.databinding.ItemListBinding

class NewsAdapter(val context: Context,  val articles: List<Article>):Adapter<NewsAdapter.ArticleViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)



        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.newsTitle.text = article.title
        holder.newsDesription.text = article.description
        holder.itemView.setOnClickListener{
            Toast.makeText(context,article.title, Toast.LENGTH_SHORT).show()
        }

        Glide.with(context).load(article.urlToImage).into(holder.newsImg)
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    class ArticleViewHolder(binding : ItemListBinding):ViewHolder(binding.root){
        var newsImg = binding.newsImage
        var newsTitle = binding.newsTitle
        var newsDesription = binding.newsDescription
    }

}