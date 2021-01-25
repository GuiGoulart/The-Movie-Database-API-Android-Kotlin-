package com.severo.the.movie.database.api.response

import com.google.gson.annotations.SerializedName

class ListMovieResponse {

    @SerializedName("page")
    var page: Int? = null
    @SerializedName("results")
    var results: List<ResultListMovieResponse>? = null
    @SerializedName("total_pages")
    var total_pages: Int? = null
    @SerializedName("total_results")
    var total_results: Int? = null

}

class ResultListMovieResponse {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("genre_ids")
    var genre_ids: List<Int?>? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("original_language")
    var original_language: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("popularity")
    var popularity: Double? = null
    @SerializedName("poster_path")
    var poster_path: String? = null
    @SerializedName("release_date")
    var release_date: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("video")
    var video: Boolean? = null
    @SerializedName("vote_average")
    var vote_average: Double? = null
    @SerializedName("vote_count")
    var vote_count: Int? = null

}