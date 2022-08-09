package com.example.mediaparkuabtesttask

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.*
import com.example.domain.source.NewsRepository
import com.example.domain.usecase.FilterUserCase
import com.example.domain.usecase.SearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val newsRepository: NewsRepository,
    val searchHistoryUseCase: SearchHistoryUseCase,
    val filterUserCase: FilterUserCase
) : ViewModel() {
    private val dispatcher = Dispatchers.IO

    var news by mutableStateOf(NewsData())
        private set
    var searchHistory by mutableStateOf(emptyList<SearchHistoryItem>())
        private set
    var filter by mutableStateOf(filterUserCase.filter)
        private set
    var sortedType by mutableStateOf("Upload date")
        private set
    /**
     * Need to ask how it`s works
     */
    var filtersCount by mutableStateOf(1)
        private set
    var sortCount by mutableStateOf(2)
        private set

    init {
        getNews()
    }

    fun changeSelectedValue(newSortedType:String){
        sortedType = newSortedType
    }
    fun getFilterFormat() = filterUserCase.filterFormat
    fun setNewFilter(newFilter: Filter) {
        filter = newFilter
    }
    fun setNewSearchIn(isTitleUsed: Boolean, isDescriptionUsed: Boolean, isContentUsed: Boolean) {
        val searchIn: SearchIn =
            filterUserCase.getSearchIn(isTitleUsed, isDescriptionUsed, isContentUsed)
        filter = Filter(filter.dateFrom, filter.dateTo, searchIn)
    }

    fun clearFilter() {
        filter = filterUserCase.filter
    }
    fun clearFilterSearchIn() {
        filter = Filter(filter.dateFrom, filter.dateTo, SearchIn.All)
    }

    fun getSearchHistory() {
        viewModelScope.launch(dispatcher) {
            searchHistory = searchHistoryUseCase.getSearchHistory()
        }
    }

    fun search(searchItem: String) {
        viewModelScope.apply {
            launch(dispatcher) {manageHistory(searchItem)}
            launch(dispatcher) {getNewsWithParams(searchItem)}
        }
    }

    private fun getNewsWithParams(searchItem: String) {
        viewModelScope.launch(dispatcher) {
            val searchParams = SearchParams(
                q = searchItem,
                _in = filter.searchIn._in,
                from = newsRepository.convertToRequest(filter.dateFrom),
                to = newsRepository.convertToRequest(filter.dateTo),
                sortby = sortedType,
            )
            news = newsRepository.searchNews(searchParams)
        }
    }

    fun manageHistory(searchItem: String) {
        val isSearchHistoryChanged = searchHistoryUseCase
            .search(SearchHistoryItem(searchItem), searchHistory)
        if (isSearchHistoryChanged) {
            getSearchHistory()
        }
    }

    fun clearSearchHistory() {
        viewModelScope.launch(dispatcher) {
            searchHistoryUseCase.cleanSearchHistory()
        }
    }

    private fun getNews() {
        viewModelScope.launch(dispatcher) {
            news = newsRepository.getNews()
        }
    }
}