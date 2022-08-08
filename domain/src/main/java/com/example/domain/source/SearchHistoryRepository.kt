package com.example.domain.source

import com.example.domain.models.SearchHistoryItem

interface SearchHistoryRepository {
    fun getSearchHistory():List<SearchHistoryItem>
    fun addSearchHistory(searchHistoryItem: SearchHistoryItem)
    fun cleanSearchHistory()
}