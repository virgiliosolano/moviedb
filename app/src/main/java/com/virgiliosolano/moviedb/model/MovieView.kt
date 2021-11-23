package com.virgiliosolano.moviedb.model

data class MovieView(
    val id: Int,
    val title: String?,
    val overview: String?,
    val popularity: Double,
    val poster_path: String
)
