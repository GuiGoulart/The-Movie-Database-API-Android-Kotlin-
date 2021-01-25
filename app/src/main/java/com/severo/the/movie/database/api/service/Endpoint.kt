package com.severo.the.movie.database.api.service

import com.severo.the.movie.database.api.response.ListMovieCastDetailsResponse
import com.severo.the.movie.database.api.response.ListMovieDetailsResponse
import com.severo.the.movie.database.api.response.ListMovieResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {

    /* Lista de filme */
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("discover/movie")
    fun listMovie(@Query(encoded = false, value = "page") page : Int?,
                  @Query(encoded = false, value = "api_key") api_key : String?,
                  @Query(encoded = false, value = "language") language : String?): Single<ListMovieResponse>

    /* Detalhe do filme escolhido */
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("movie/{movieID}")
    fun listMovieDetails(@Path("movieID") movieID: Int?,
                         @Query(encoded = false, value = "api_key") api_key : String?,
                         @Query(encoded = false, value = "language") language : String?): Call<ListMovieDetailsResponse>

    /* Detalhe do elenco do filme */
    @Headers("Content-Type: application/json;charset=utf-8")
    @GET("movie/{movieID}/credits")
    fun listMovieCastDetails(@Path("movieID") movieID: Int?,
                         @Query(encoded = false, value = "api_key") api_key : String?,
                         @Query(encoded = false, value = "language") language : String?): Call<ListMovieCastDetailsResponse>

}