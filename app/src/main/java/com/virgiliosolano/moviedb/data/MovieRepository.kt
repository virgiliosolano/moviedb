package com.virgiliosolano.moviedb.data

import com.virgiliosolano.moviedb.data.local.MovieDao
import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.data.local.entity.MovieEntityList
import com.virgiliosolano.moviedb.data.mapper.toMovieEntity
import com.virgiliosolano.moviedb.data.remote.MovieRemoteDataSource
import com.virgiliosolano.moviedb.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieDao: MovieDao
) {

    suspend fun fetchMovieList(): Flow<Result<MovieEntityList>?> {
        return flow {
            emit(fetchMovieListCached())
            emit(Result.loading())

            var movieEntityList = arrayListOf<Movie>()
            val result = movieRemoteDataSource.fetchMovieList()

            if (result.status == Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    movieDao.clearMovies()
                    it.map { movie ->
                        movieEntityList.add(movie.toMovieEntity())
                    }
                    movieDao.insertMovies(movieEntityList)
                }
                emit(Result.success(MovieEntityList(movieEntityList)))
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fetchMovieListCached(): Result<MovieEntityList>? =
        movieDao.getAll()?.let {
            Result.success(MovieEntityList(it))
        }

    suspend fun fetchMovie(id: Int): Flow<Result<Movie>> {
        return flow {
            emit(Result.loading())
            emit(Result.success(movieDao.getMovieById(id)))

            val result = movieRemoteDataSource.fetchMovie(id)

            if (result.status == Result.Status.SUCCESS) {
                result.data?.let {
                    val movieEntity = it.toMovieEntity()
                    movieDao.insertMovie(movieEntity)
                    emit(Result.success(movieEntity))
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}