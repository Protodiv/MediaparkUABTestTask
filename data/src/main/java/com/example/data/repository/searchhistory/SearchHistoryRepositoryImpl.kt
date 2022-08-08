package com.example.data.repository.searchhistory

import com.example.data.models.HistoryItem
import com.example.domain.models.SearchHistoryItem
import com.example.domain.source.SearchHistoryRepository
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    private val historyItemDao: HistoryItemDao
): SearchHistoryRepository {
    override fun getSearchHistory() =
        historyItemDao.getAll().map{ it.toSearchHistoryItem() }

    override fun addSearchHistory(searchHistoryItem: SearchHistoryItem) =
        historyItemDao.insertAll(
            HistoryItem(
                searchHistoryItem
            )
        )

    override fun cleanSearchHistory() {
        historyItemDao.deleteAll()
    }
}