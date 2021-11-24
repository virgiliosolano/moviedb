package com.virgiliosolano.moviedb.ui.moviedetails

import androidx.lifecycle.*
import com.virgiliosolano.moviedb.data.MovieRepository
import com.virgiliosolano.moviedb.data.local.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import com.virgiliosolano.moviedb.util.Result

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private var _id = MutableLiveData<Int>()
    private val _movie: LiveData<Result<Movie>> = _id.distinctUntilChanged().switchMap { movieID ->
        liveData {
            movieRepository.fetchMovie(movieID).onStart {
                emit(Result.loading())
            }.collect {
                emit(it)
            }
        }
    }
    val movie = _movie

    fun getMovieDetails(id: Int) {
        _id.value = id
    }
}