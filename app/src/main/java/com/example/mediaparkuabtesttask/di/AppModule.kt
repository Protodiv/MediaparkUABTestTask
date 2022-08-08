package com.example.mediaparkuabtesttask.di

import android.content.Context
import androidx.room.Room
import com.example.data.repository.newsrepository.GNewsService
import com.example.data.repository.newsrepository.NewsRepositoryImpl
import com.example.data.repository.searchhistory.HistoryItemDao
import com.example.data.repository.searchhistory.SearchHistoryDataBase
import com.example.data.repository.searchhistory.SearchHistoryRepositoryImpl
import com.example.domain.source.NewsRepository
import com.example.domain.source.SearchHistoryRepository
import com.example.domain.usecase.FilterUserCase
import com.example.domain.usecase.SearchHistoryUseCase
import com.example.mediaparkuabtesttask.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl() : String = "https://gnews.io/api/v4/"

    @Provides
    fun providesLogging() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesHttpClient(logging:HttpLoggingInterceptor) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String, client:OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideNewsService(retrofit : Retrofit) : GNewsService = retrofit.create(GNewsService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepo(newsService: GNewsService) : NewsRepository = NewsRepositoryImpl(newsService)

    @Singleton
    @Provides
    fun provideSearchHistoryDataBase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        SearchHistoryDataBase::class.java,
        "searchHistoryDB"
    ).build()

    @Singleton
    @Provides
    fun provideHistoryItemDao(db: SearchHistoryDataBase) = db.historyItemDao()

    @Provides
    @Singleton
    fun provideSearchHistoryRepo(
        historyItemDao: HistoryItemDao
    ) : SearchHistoryRepository = SearchHistoryRepositoryImpl(historyItemDao)
}

@Module
@InstallIn(ActivityComponent::class)
internal object UseCases {
    @Provides
    @ViewModelScoped
    fun provideSearchHistoryUseCase(
        searchHistoryRepository: SearchHistoryRepository
    ) = SearchHistoryUseCase(searchHistoryRepository)

    @Provides
    @ViewModelScoped
    fun provideFilterUserCase() = FilterUserCase()
}

@Module
@InstallIn(ViewModelComponent::class)
internal object MainViewModelModule {
    @Provides
    @ViewModelScoped
    fun provideMainViewModel(
        newsRepository: NewsRepository,
        searchHistoryUseCase: SearchHistoryUseCase,
        filterUserCase:FilterUserCase
    ) = MainViewModel(newsRepository,searchHistoryUseCase,filterUserCase)
}

