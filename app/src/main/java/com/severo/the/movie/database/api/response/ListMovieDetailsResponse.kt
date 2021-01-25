package com.severo.the.movie.database.api.response

import com.google.gson.annotations.SerializedName

class ListMovieDetailsResponse {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null
    @SerializedName("belongs_to_collection")
    var belongs_to_collection: BelongsToCollectionListMovieDetailsResponse? = null
    @SerializedName("budget")
    var budget: Int? = null
    @SerializedName("genres")
    var genres: List<GenresListMovieDetailsResponse>? = null
    @SerializedName("homepage")
    var homepage: String? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("imdb_id")
    var imdb_id: String? = null
    @SerializedName("original_language")
    var original_language: String? = null
    @SerializedName("original_title")
    var original_title: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("popularity")
    var popularity: Double? = null
    @SerializedName("poster_path")
    var poster_path: String? = null
    @SerializedName("production_companies")
    var production_companies: List<ProductionCompaniesListMovieDetailsResponse>? = null
    @SerializedName("production_countries")
    var production_countries: List<ProductionCountriesListMovieDetailsResponse>? = null
    @SerializedName("release_date")
    var release_date: String? = null
    @SerializedName("revenue")
    var revenue: Int? = null
    @SerializedName("runtime")
    var runtime: Int? = null
    @SerializedName("spoken_languages")
    var spoken_languages: List<SpokenLanguagesListMovieDetailsResponse>? = null
    @SerializedName("status")
    var status: String? = null
    @SerializedName("tagline")
    var tagline: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("video")
    var video: Boolean? = null
    @SerializedName("vote_average")
    var vote_average: Float? = null
    @SerializedName("vote_count")
    var vote_count: Int? = null

}

class BelongsToCollectionListMovieDetailsResponse {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("poster_path")
    var poster_path: String? = null
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null

}

class GenresListMovieDetailsResponse {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("name")
    var name: String? = null

}

class ProductionCompaniesListMovieDetailsResponse {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("logo_path")
    var nalogo_pathme: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("origin_country")
    var origin_country: String? = null

}

class ProductionCountriesListMovieDetailsResponse {

    @SerializedName("iso_3166_1")
    var iso_3166_1: String? = null
    @SerializedName("name")
    var name: String? = null

}

class SpokenLanguagesListMovieDetailsResponse {

    @SerializedName("english_name")
    var english_name: String? = null
    @SerializedName("iso_639_1")
    var iso_639_1: String? = null
    @SerializedName("name")
    var name: String? = null

}