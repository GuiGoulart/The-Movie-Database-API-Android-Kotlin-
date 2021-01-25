package com.severo.the.movie.database.api.retrofit

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class RetrofitApi {
    companion object {
        var retrofit: Retrofit? = null
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private val client = OkHttpClient.Builder()
            .addInterceptor { chain: Interceptor.Chain ->
                var newRequest = chain.request()
                val resp = chain.proceed(newRequest)
                resp
            }.readTimeout(1, TimeUnit.MINUTES).addNetworkInterceptor(StethoInterceptor()).build()
        val retrofitInstance: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }
}