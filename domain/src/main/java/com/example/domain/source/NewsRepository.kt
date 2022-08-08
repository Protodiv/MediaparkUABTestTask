package com.example.domain.source

import com.example.domain.models.NewsData
import com.example.domain.models.SearchParams
import java.time.LocalDate

interface NewsRepository {
    suspend fun getNews(): NewsData
    suspend fun searchNews(searchParams: SearchParams): NewsData
    fun convertToRequest(dateFrom: LocalDate?):String?
}