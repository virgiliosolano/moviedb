package com.virgiliosolano.moviedb.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.virgiliosolano.moviedb.R
import com.virgiliosolano.moviedb.data.local.entity.Movie
import com.virgiliosolano.moviedb.model.MovieView

fun ImageView.loadImage(string: String?) {
    Glide.with(context).load(string ?: R.mipmap.ic_launcher).into(this)
}