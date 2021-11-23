package com.virgiliosolano.moviedb.ui.moviedetails

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.virgiliosolano.moviedb.R
import com.virgiliosolano.moviedb.extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_details.*

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent?.getIntExtra(MOVIE_ID, 0)?.let { id ->
            Log.d("MOVIE_ID", id.toString())
        }

        intent?.getStringExtra(MOVIE_NAME)?.let { name ->
            supportActionBar?.title = name
            Log.d("MOVIE_NAME", MOVIE_NAME)
        }


        ivMoviePosterDetail.loadImage("https://image.tmdb.org/t/p/w500/cinER0ESG0eJ49kXlExM0MEWGxW.jpg")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_NAME = "movie_name"
    }
}