package com.virgiliosolano.moviedb.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.virgiliosolano.moviedb.R
import com.virgiliosolano.moviedb.extensions.launchActivity
import com.virgiliosolano.moviedb.ui.moviedetails.MovieDetailsActivity
import com.virgiliosolano.moviedb.ui.moviedetails.MovieDetailsActivity.Companion.MOVIE_ID
import com.virgiliosolano.moviedb.ui.moviedetails.MovieDetailsActivity.Companion.MOVIE_NAME
import dagger.hilt.android.AndroidEntryPoint
import com.virgiliosolano.moviedb.util.Result
import kotlinx.android.synthetic.main.activity_movies.*

@AndroidEntryPoint
class MoviesActivity : OnItemClickListener, AppCompatActivity() {

    private val moviesViewModel by viewModels<MoviesViewModel>()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setupView()
        subscribeUi()
    }

    private fun setupView() {
        title = getString(R.string.app_name)

        LinearLayoutManager(this).let { llManager ->
            rvMovies.layoutManager = llManager
            rvMovies.addItemDecoration(
                DividerItemDecoration(
                    rvMovies.context,
                    0
            ))
        }

        moviesAdapter = MoviesAdapter(ArrayList(), this).also {
            rvMovies.adapter = it
        }
    }

    private fun subscribeUi() {
        moviesViewModel.movieList.observe(this, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    result.data?.results?.let { list ->
                        moviesAdapter.updateMovies(list)
                    }
                    ivLogo.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        Toast.makeText(applicationContext, it, Toast.LENGTH_LONG)
                    }
                    ivLogo.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    ivLogo.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onItemClick(movieId: Int, movieName: String) {
        launchActivity<MovieDetailsActivity> {
            putExtra(MOVIE_ID, movieId)
            putExtra(MOVIE_NAME, movieName)
        }
    }
}