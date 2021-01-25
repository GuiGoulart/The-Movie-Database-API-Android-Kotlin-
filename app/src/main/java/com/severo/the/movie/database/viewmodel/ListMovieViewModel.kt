package com.severo.the.movie.database.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.severo.the.movie.database.api.data.State
import com.severo.the.movie.database.api.response.ResultListMovieResponse
import com.severo.the.movie.database.api.retrofit.ApiRequest
import com.severo.the.movie.database.api.data.NewsDataSource
import com.severo.the.movie.database.api.data.NewsDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class ListMovieViewModel: ViewModel() {

    val request = ApiRequest()
    var newsList: LiveData<PagedList<ResultListMovieResponse>>
    val compositeDisposable = CompositeDisposable()
    val pageSize = 5
    var newsDataSourceFactory: NewsDataSourceFactory
    var search: String? = ""

    init {
        newsDataSourceFactory = NewsDataSourceFactory(compositeDisposable, request, search)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        newsList = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap(
        newsDataSourceFactory.newsDataSourceLiveData,
        NewsDataSource::state
    )

    fun retry() {
        newsDataSourceFactory.newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return newsList.value?.isEmpty() ?: true
    }

    fun searchActivity(search: String?){
        this.search = search
        newsDataSourceFactory.searchActivity(search)
        newsDataSourceFactory.newsDataSourceLiveData.value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}