package com.virgiliosolano.moviedb.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virgiliosolano.moviedb.data.MovieRepository
import com.virgiliosolano.moviedb.data.local.entity.MovieEntityList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import com.virgiliosolano.moviedb.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    private val _movieList = MutableLiveData<Result<MovieEntityList>>()
    val movieList = _movieList

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            movieRepository.fetchMovieList().collect { result ->
                _movieList.value = result
            }
        }
    }
}