package com.severo.the.movie.database.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.severo.the.movie.database.api.BaseCallbackApi
import com.severo.the.movie.database.api.response.ListMovieDetailsResponse
import com.severo.the.movie.database.api.retrofit.ApiRequest
import retrofit2.Call
import retrofit2.Response

class ListMovieDetailsViewModel : ViewModel() {

    private val request = ApiRequest()

    fun listMovieDetails(context: Context, movieID: Int?): LiveData<ListMovieDetailsResponse?> {
        val liveData = MutableLiveData<ListMovieDetailsResponse?>()
        request.services!!.listMovieDetails(movieID, "3650bfa0484949e5ae5a109faca31aa3", "pt-BR")?.enqueue(object : BaseCallbackApi<ListMovieDetailsResponse?>(context) {
            override fun onResponse(
                call: Call<ListMovieDetailsResponse?>,
                response: Response<ListMovieDetailsResponse?>
            ) {
                super.onResponse(call, response)
                liveData.value = if (response.isSuccessful) response.body() else null
                Log.d("errorSuccess", response.toString())
            }

            override fun onFailure(call: Call<ListMovieDetailsResponse?>, t: Throwable) {
                super.onFailure(call, t)
                Log.d("errorFailed", t.toString())
                liveData.value = null
            }
        })
        return liveData
    }

}