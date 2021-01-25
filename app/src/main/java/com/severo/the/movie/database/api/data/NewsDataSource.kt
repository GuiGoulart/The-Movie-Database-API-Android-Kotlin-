package com.severo.the.movie.database.api.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.severo.the.movie.database.api.data.State.DONE
import com.severo.the.movie.database.api.data.State.ERROR
import com.severo.the.movie.database.api.response.ResultListMovieResponse
import com.severo.the.movie.database.api.retrofit.ApiRequest
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class NewsDataSource(
    private val request: ApiRequest,
    private val compositeDisposable: CompositeDisposable,
    private val search: String? = "")
    : PageKeyedDataSource<Int, ResultListMovieResponse>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ResultListMovieResponse>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            request.services?.listMovie(1, "3650bfa0484949e5ae5a109faca31aa3", "pt-BR")
                ?.subscribe(
                    {response ->
                        Log.d("errorTeste", response.toString())
                        updateState(DONE)
                        callback.onResult(response.results!!,
                            null,
                            2
                        )
                    },
                    {
                        Log.d("errorTeste", it.message.toString())
                        updateState(ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )!!
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultListMovieResponse>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            request.services?.listMovie(params.key, "3650bfa0484949e5ae5a109faca31aa3", "pt-BR")
                ?.subscribe(
                    { response ->
                        Log.d("errorTeste", response.toString())
                        updateState(DONE)
                        callback.onResult(response.results!!,
                            params.key - 1
                        )
                    },
                    {
                        Log.d("errorTeste", it.toString())
                        updateState(ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )!!
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultListMovieResponse>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            request.services?.listMovie(params.key, "3650bfa0484949e5ae5a109faca31aa3", "pt-BR")
                ?.subscribe(
                    { response ->
                        Log.d("errorTeste", response.toString())
                        updateState(DONE)
                        callback.onResult(response.results!!,
                            params.key + 1
                        )
                    },
                    {
                        Log.d("errorTeste", it.toString())
                        updateState(ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )!!
        )
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}