package com.virgiliosolano.moviedb.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virgiliosolano.moviedb.BuildConfig
import com.virgiliosolano.moviedb.R
import com.virgiliosolano.moviedb.extensions.loadImage
import com.virgiliosolano.moviedb.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesAdapter(private val movieList: ArrayList<Movie>,
                    private val onItemClickListener: OnItemClickListener ) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie, onItemClickListener: OnItemClickListener) {
            itemView.apply {
                this.tvMovieTitle.text = movie.title
                this.ivMoviePoster.loadImage(BuildConfig.TMDB_POSTER_URL.plus(movie.poster_path))
                this.setOnClickListener {onItemClickListener.onItemClick(movie.id)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
            .let {
                return MovieViewHolder(it)
        }
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position], onItemClickListener)
    }

    fun updateMovies(movieList: List<Movie>) {
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }
}