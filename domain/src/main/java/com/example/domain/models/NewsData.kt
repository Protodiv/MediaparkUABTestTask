package com.example.domain.models

data class NewsData(
    val totalArticles: Int = 0,
    val articles:Array<Article> = emptyArray()
)

data class Article(
    val title:String,
    val description:String,
    val content:String,
    val url:String,
    val image:String,
    val publishedAt:String,
    val source:Source,
)

data class Source(
    val name:String,
    val url:String
)