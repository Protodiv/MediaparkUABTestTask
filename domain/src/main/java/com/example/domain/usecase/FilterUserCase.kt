package com.example.domain.usecase

import com.example.domain.models.Filter
import com.example.domain.models.SearchIn
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FilterUserCase @Inject constructor() {
    var filter = Filter()

    var filterFormat = "uuuu/MM/d"

    fun getSearchIn(titleUsed: Boolean, descriptionUsed: Boolean, contentUsed: Boolean): SearchIn{
        val searchInList = mutableListOf<SearchIn>()

        if(titleUsed){
            searchInList.add(SearchIn.Title)
        }
        if(descriptionUsed){
            searchInList.add(SearchIn.Description)
        }
        if(contentUsed){
            searchInList.add(SearchIn.Content)
        }

        val allUsed = listOf(titleUsed , descriptionUsed , contentUsed)
        println("$titleUsed ::: $descriptionUsed ::: $contentUsed")
        println("${allUsed.all { it }}")
        println("${allUsed.all { !it }}")
        if (allUsed.all { it } || allUsed.all { !it }) {
            return SearchIn.All
        }
        val searchIn = SearchIn.Custom.apply {
            _in = searchInList.joinToString { it._in+"," }.drop(2)
            title = searchInList.joinToString{it.title + " "}
        }
        return searchIn
    }

}