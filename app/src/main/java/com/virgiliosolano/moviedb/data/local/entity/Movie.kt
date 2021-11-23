package com.virgiliosolano.moviedb.data.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @NonNull
    @PrimaryKey
    val id: Int,
    val title: String?,
    val overview: String?,
    val popularity: Double,
    val posterUrl: String?,
    val backdropUrl: String?
)
