package com.virgiliosolano.moviedb.network.services

import com.virgiliosolano.moviedb.data.remote.entity.MovieListResponse
import com.virgiliosolano.moviedb.data.remote.entity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("movie/popular")
    suspend fun getMovies() : Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int) : Response<MovieResponse>
}