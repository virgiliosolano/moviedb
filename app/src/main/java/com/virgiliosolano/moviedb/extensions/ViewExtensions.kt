package com.virgiliosolano.moviedb.extensions

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.virgiliosolano.moviedb.R

fun ImageView.loadImage(string: String?) {
    Glide.with(context)
        .load(string ?: R.mipmap.ic_launcher)
        .placeholder(R.drawable.ic_movie)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .transition(DrawableTransitionOptions.withCrossFade())
        .priority(Priority.IMMEDIATE)
        .encodeFormat(Bitmap.CompressFormat.PNG)
        .format(DecodeFormat.DEFAULT)
        .into(this)

}