package com.severo.the.movie.database.api.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.severo.the.movie.database.api.response.ResultListMovieResponse
import com.severo.the.movie.database.api.retrofit.ApiRequest
import io.reactivex.disposables.CompositeDisposable

class NewsDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val request: ApiRequest,
    var search: String? = "")
    : DataSource.Factory<Int, ResultListMovieResponse>() {

    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, ResultListMovieResponse> {
        val newsDataSource = NewsDataSource(request, compositeDisposable, search)
        newsDataSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }

    fun searchActivity(search: String?){
        this.search = search
    }

}