package com.example.api_integration_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView.OnScrollListener
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api_integration_example.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResult = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NewsAdapter(this@MainActivity,articles)
        binding.newsList.adapter = adapter

        val lm = LinearLayoutManager(this@MainActivity)
        binding.newsList.layoutManager = lm


        binding.newsList.addOnScrollListener(object : androidx.recyclerview.widget.RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (totalResult > lm.itemCount && lm.findFirstVisibleItemPosition() >= lm.itemCount-7)
                {
                    pageNum++
                    getNews()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        getNews()


    }

    private fun getNews() {
        val news = NewsService.newsInstance.getHeadlines("us",pageNum)
        news.enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news = response.body()
                if (news != null) {
                    totalResult = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Error in Fetching News $t",Toast.LENGTH_SHORT).show()
            }
        })
    }
}