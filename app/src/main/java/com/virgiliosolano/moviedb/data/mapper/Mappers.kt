package com.virgiliosolano.moviedb.data.mapper

import com.google.gson.annotations.SerializedName
import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.data.remote.entity.MovieResponse

fun MovieResponse.toMovieEntity(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        popularity= popularity,
        posterUrl = posterLink,
        backdropUrl = backdropLink,
        homepage = homepage,
        originalTitle = originalTitle,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        genres = genresFormatted
    )
}