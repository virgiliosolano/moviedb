package com.virgiliosolano.moviedb.ui.movies

import com.virgiliosolano.moviedb.data.local.entity.Movie

interface MovieView {
    fun bindMovieList(movieList: List<Movie>)
}