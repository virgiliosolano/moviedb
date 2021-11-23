package com.virgiliosolano.moviedb.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.virgiliosolano.moviedb.R

fun ImageView.loadImage(string: String?) {
    Glide.with(context)
        .load(string ?: R.mipmap.ic_launcher)
        .placeholder(R.mipmap.ic_launcher)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

}