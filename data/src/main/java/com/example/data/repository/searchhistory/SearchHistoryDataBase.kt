package com.example.data.repository.searchhistory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.HistoryItem

@Database(entities = [HistoryItem::class], version = 2)
abstract class SearchHistoryDataBase : RoomDatabase() {
    abstract fun historyItemDao(): HistoryItemDao
}