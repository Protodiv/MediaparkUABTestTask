package com.example.data.repository.searchhistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.models.HistoryItem

@Dao
interface HistoryItemDao {
    @Query("SELECT * FROM historyItem")
    fun getAll(): List<HistoryItem>

    @Insert
    fun insertAll(vararg historyItem: HistoryItem)

    @Query("DELETE FROM historyItem")
    fun deleteAll()
}