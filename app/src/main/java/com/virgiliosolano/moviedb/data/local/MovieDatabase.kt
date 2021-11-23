package com.virgiliosolano.moviedb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.virgiliosolano.moviedb.data.local.entity.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}