package com.virgiliosolano.moviedb.data.mapper

import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.data.remote.entity.MovieResponse

fun MovieResponse.toMovieEntity(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        popularity= popularity,
        posterUrl = posterLink,
        backdropUrl = backdropLink
    )
}