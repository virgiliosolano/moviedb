package com.virgiliosolano.moviedb.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.virgiliosolano.moviedb.BuildConfig.TMDB_POSTER_MEDIUM_URL

data class MovieResponse(
    val id: Int,
    val title: String?,
    val overview: String?,
    val popularity: Double,
    val homepage: String,
    val genres: List<Genre>,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double
){
    val posterLink: String?
        get() =
            if (posterPath != null) TMDB_POSTER_MEDIUM_URL.plus("$posterPath") else null
    val backdropLink: String?
        get() =
            if (backdropPath != null) TMDB_POSTER_MEDIUM_URL.plus("$backdropPath") else null
    val genresFormatted : String
        get() = {
            val genreList = ""
            genres.forEach {
                genreList.plus(it.name).plus(" ")
            }
            genreList
        }.toString()
}

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)


