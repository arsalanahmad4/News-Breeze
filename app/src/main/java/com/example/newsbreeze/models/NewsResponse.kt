package com.example.newsbreeze.models


import com.example.newsbreeze.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)