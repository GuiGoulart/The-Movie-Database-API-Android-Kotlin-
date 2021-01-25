package com.severo.the.movie.database.api.response

import com.google.gson.annotations.SerializedName

class   ListMovieCastDetailsResponse {

    @SerializedName("id")
    var id: Int? = null
    @SerializedName("cast")
    var cast: List<CastListMovieCastDetailsResponse>? = null
    @SerializedName("crew")
    var crew: List<CrewListMovieCastDetailsResponse>? = null

}

class CastListMovieCastDetailsResponse {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("gender")
    var gender: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("known_for_department")
    var known_for_department: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("original_name")
    var original_name: String? = null
    @SerializedName("popularity")
    var popularity: Double? = null
    @SerializedName("profile_path")
    var profile_path: String? = null
    @SerializedName("cast_id")
    var cast_id: Int? = null
    @SerializedName("character")
    var character: String? = null
    @SerializedName("credit_id")
    var credit_id: String? = null
    @SerializedName("order")
    var order: Int? = null

}

class CrewListMovieCastDetailsResponse {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("gender")
    var gender: Int? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("known_for_department")
    var known_for_department: String? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("original_name")
    var original_name: String? = null
    @SerializedName("popularity")
    var popularity: Double? = null
    @SerializedName("profile_path")
    var profile_path: String? = null
    @SerializedName("credit_id")
    var credit_id: String? = null
    @SerializedName("department")
    var department: String? = null
    @SerializedName("job")
    var job: String? = null

}