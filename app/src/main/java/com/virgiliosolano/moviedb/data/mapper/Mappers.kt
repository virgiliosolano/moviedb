package com.virgiliosolano.moviedb.data.mapper

import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.data.remote.entity.MovieResponse

fun MovieResponse.toMovieEntity(): Movie {

    var genreList : String? = ""
    if (genres.isNullOrEmpty().not()) {
        genres.forEach {
            genreList = genreList.plus(it.name).plus(" ")
        }
    }
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
        genres = genreList
    )
}