package com.example.api_integration_example

import com.google.gson.annotations.SerializedName

data class Article(
    var author: String,
    @SerializedName("title")
    var Artical_title: String,
    var description: String,
    var url: String,
    var urlToImage: String
)
