package com.virgiliosolano.moviedb.data.remote

import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.data.remote.entity.MovieListResponse
import com.virgiliosolano.moviedb.network.services.MovieService
import com.virgiliosolano.moviedb.util.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import com.virgiliosolano.moviedb.util.Result
import com.google.gson.annotations.SerializedName
import com.virgiliosolano.moviedb.data.remote.entity.MovieResponse


private const val FETCH_MOVIE_LIST_ERROR = "An error occurred when fetching MovieView list"
private const val FETCH_MOVIE_ERROR = "An error occurred when fetching MovieView"
private const val UNKNOWN_ERROR = "Unknown Error"

class MovieRemoteDataSource @Inject constructor(private val retrofit: Retrofit) {

    suspend fun fetchMovieList(): Result<MovieListResponse> {
        retrofit.create(MovieService::class.java).run {
            return getResponse(
                request = { this.getMovies() },
                defaultErrorMessage = FETCH_MOVIE_LIST_ERROR
            )
        }
    }

    suspend fun fetchMovie(id: Int): Result<MovieResponse> {
        retrofit.create(MovieService::class.java).run {
            return getResponse(
                request = { this.getMovie(id) },
                defaultErrorMessage = FETCH_MOVIE_ERROR
            )
        }
    }

    private suspend fun <T> getResponse(
        request: suspend () -> Response<T>,
        defaultErrorMessage: String
    ): Result<T> {
        return try {
            request.invoke().run {
                return if (this.isSuccessful) {
                    Result.success(this.body())
                } else {
                    val errorResponse = ErrorUtils.parseError(this, retrofit)
                    Result.error(errorResponse?.message ?: defaultErrorMessage, errorResponse)
                }
            }
        } catch (e: Throwable) {
            Result.error(UNKNOWN_ERROR, null)
        }
    }
}