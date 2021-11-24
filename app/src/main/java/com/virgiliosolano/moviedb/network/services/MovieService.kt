package com.virgiliosolano.moviedb.network.services

import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.data.remote.entity.MovieListResponse
import com.virgiliosolano.moviedb.data.remote.entity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    // /trending/{media_type}/{time_window}
    @GET("trending/movie/week")
    suspend fun getMovies() : Response<MovieListResponse>

    /*@GET("/movie/popular")
    suspend fun getMovies() : Response<MovieListResponse>*/

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieResponse>
}