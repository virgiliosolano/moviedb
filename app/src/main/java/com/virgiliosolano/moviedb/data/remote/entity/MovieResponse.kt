package com.virgiliosolano.moviedb.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.virgiliosolano.moviedb.BuildConfig.TMDB_POSTER_MEDIUM_URL

data class MovieResponse(
    val id: Int,
    val title: String?,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
){
    val posterLink: String?
        get() =
            if (posterPath != null) TMDB_POSTER_MEDIUM_URL.plus("$posterPath") else null
}
