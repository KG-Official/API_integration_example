package com.example.api_integration_example

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=884d64a41d3b41b39894026907fc0163
//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=884d64a41d3b41b39894026907fc0163

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "ec37cd2f412d42ca9409d6b3beb2c73c"

interface NewsApi_Interface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country:String,@Query("page")page:Int):Call<News>

}

object NewsService{

    val newsInstance : NewsApi_Interface

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        newsInstance = retrofit.create(NewsApi_Interface::class.java)
    }
}