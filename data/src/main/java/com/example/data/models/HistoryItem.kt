package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.SearchHistoryItem

@Entity
data class HistoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "historyItem") val historyItem: String?,
){
    constructor(searchHistoryItem: SearchHistoryItem):this(
        id = 0,
        historyItem = searchHistoryItem.historyItem
    )

    fun toSearchHistoryItem(): SearchHistoryItem {
        return SearchHistoryItem(
            historyItem = historyItem
        )
    }
}
