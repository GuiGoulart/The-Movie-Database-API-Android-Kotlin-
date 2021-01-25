package com.severo.the.movie.database.api.retrofit

import com.severo.the.movie.database.api.retrofit.RetrofitApi.Companion.retrofit
import com.severo.the.movie.database.api.retrofit.RetrofitApi.Companion.retrofitInstance
import com.severo.the.movie.database.api.service.Endpoint

class ApiRequest {

    val services: Endpoint?
        get() = retrofit?.create(Endpoint::class.java)

    init {
        retrofitInstance
    }

}