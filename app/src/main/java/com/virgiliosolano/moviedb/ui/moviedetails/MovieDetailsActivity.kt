package com.virgiliosolano.moviedb.ui.moviedetails

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.virgiliosolano.moviedb.util.Result
import com.virgiliosolano.moviedb.R
import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_details.*

private const val UNKNOWN_MOVIE = "Unknown Movie"

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getExtras()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun getExtras() {
        intent?.getIntExtra(MOVIE_ID, 0)?.let { id ->
            viewModel.getMovieDetails(id)
            observeMovie()
        } ?: showError(UNKNOWN_MOVIE)

        intent?.getStringExtra(MOVIE_NAME)?.let { name ->
            supportActionBar?.title = name
        } ?: showError(UNKNOWN_MOVIE)
    }

    private fun observeMovie() {
        viewModel.movie.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.let {
                        showMovieDetails(it)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        showError(it)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun showError(msg: String) {
        Snackbar.make(svContentDetails, msg, Snackbar.LENGTH_INDEFINITE).setAction("DISMISS") {
        }.show()
    }

     private fun showMovieDetails(movie: Movie) {
         ivMoviePosterDetail.loadImage(movie.backdropUrl)
         tvMovieTitle.text = movie.originalTitle
         tvDescription.text = movie.overview
         tvMovieGenre.text = movie.genres
         tvMovieHomePage.text = getString(R.string.homepage, movie.homepage)
         tvMoviePopularity.text = getString(R.string.popularity_views,movie.popularity.toString())
         tvReleaseDate.text = getString(R.string.releaseDate, movie.releaseDate)
         tvVoteAverage.text = getString(R.string.average, movie.voteAverage.toString())
    }

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_NAME = "movie_name"
    }
}