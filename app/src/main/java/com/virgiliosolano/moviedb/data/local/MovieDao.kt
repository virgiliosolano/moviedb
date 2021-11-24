package com.virgiliosolano.moviedb.data.local

import androidx.room.*
import com.virgiliosolano.moviedb.data.local.entity.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Query("DELETE FROM movie")
    fun clearMovies()

    @Query("SELECT * FROM movie order by id ASC")
    fun getAll(): List<Movie>?

    @Query("SELECT * FROM movie WHERE id = :movieID LIMIT 1")
    fun getMovieById(movieID: Int): Movie?
}