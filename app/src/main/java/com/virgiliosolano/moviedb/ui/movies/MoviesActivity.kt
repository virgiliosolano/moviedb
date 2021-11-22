package com.virgiliosolano.moviedb.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.virgiliosolano.moviedb.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MoviesActivity : OnItemClickListener, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        title = getString(R.string.app_name)

        LinearLayoutManager(this).let { llManager ->
            rvMovies.layoutManager = llManager
            rvMovies.addItemDecoration(
                DividerItemDecoration(
                    rvMovies.context,
                    llManager.orientation
            ))
        }

        MoviesAdapter(ArrayList(), this).also {
            rvMovies.adapter = it
        }
    }

    override fun onItemClick(movieId: Int) {}
}