package com.example.domain.usecase

import com.example.domain.models.SearchHistoryItem
import com.example.domain.source.SearchHistoryRepository
import javax.inject.Inject

class SearchHistoryUseCase @Inject constructor(
    private val repository: SearchHistoryRepository
) {
    fun search(searchHistoryItem: SearchHistoryItem, currentList: List<SearchHistoryItem>):Boolean {
        val isAlreadyAdded = currentList.contains(searchHistoryItem)
        if(!isAlreadyAdded){
            addSearchHistory(searchHistoryItem)
        }
        return !isAlreadyAdded
    }

    fun getSearchHistory() = repository.getSearchHistory().asReversed()

    private fun addSearchHistory(searchHistoryItem: SearchHistoryItem) {
        repository.addSearchHistory(searchHistoryItem)
    }

    fun cleanSearchHistory() = repository.cleanSearchHistory()
}