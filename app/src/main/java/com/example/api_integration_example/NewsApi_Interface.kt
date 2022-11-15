package com.example.api_integration_example

import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=884d64a41d3b41b39894026907fc0163
//https://newsapi.org/v2/everything?domains=wsj.com&apiKey=884d64a41d3b41b39894026907fc0163

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "884d64a41d3b41b39894026907fc0163"
interface NewsApi_Interface {

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country:String)
}