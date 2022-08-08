package com.example.data.repository.newsrepository

import com.example.domain.models.NewsData
import com.example.domain.models.SearchParams
import com.example.domain.source.NewsRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsService : GNewsService
):NewsRepository {

    var requestFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"

    override suspend fun getNews() = try {
        newsService.getNews()
    }catch (e:Exception){
        e.printStackTrace()
        NewsData()
    }

    override suspend fun searchNews(searchParams: SearchParams): NewsData = try {
        newsService.search(
            q = "\"" + searchParams.q + "\"",
            _in = searchParams._in,
            from = searchParams.from,
            to = searchParams.to,
            sortby = searchParams.sortby,
        )
    }catch (e:Exception){
        e.printStackTrace()
        NewsData()
    }

    override fun convertToRequest(dateFrom: LocalDate?):String? {
        if(dateFrom == null){
            return null
        }
        return dateFrom.atTime(1,1,1)?.format(DateTimeFormatter.ofPattern(requestFormat))
    }
}