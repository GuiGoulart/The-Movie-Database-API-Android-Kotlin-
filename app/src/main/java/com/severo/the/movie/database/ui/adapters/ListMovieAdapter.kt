package com.severo.the.movie.database.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.severo.the.movie.database.R
import com.severo.the.movie.database.api.data.State
import com.severo.the.movie.database.api.response.ResultListMovieResponse
import com.severo.the.movie.database.ui.activities.DetailsMoviesActivity
import com.severo.the.movie.database.ui.activities.ServerErrorActivity
import com.severo.the.movie.database.util.Util
import kotlinx.android.synthetic.main.rv_list_movie.view.*

class ListMovieAdapter (var retry: () -> Unit) : PagedListAdapter<ResultListMovieResponse, ListMovieAdapter.ListMovieAdapterViewHolder>(NewsDiffCallback) {

    private var list: List<ResultListMovieResponse>? = null
    private lateinit var context: Context

    private var state = State.LOADING

    fun setcontext(context: Context?) {
        this.context = context!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ListMovieAdapterViewHolder {
        return ListMovieAdapterViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_list_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (super.getItemCount() == null) {
            0
        } else super.getItemCount()
    }

    override fun onBindViewHolder(holder: ListMovieAdapterViewHolder, position: Int) {
        val list = getItem(position)

        holder.textMovie.text = list?.title
        Glide.with(context).load("http://image.tmdb.org/t/p/w500${list?.poster_path}").into(holder.imageMovie)
        Util.loadImage(context, "http://image.tmdb.org/t/p/w500${list?.poster_path}", holder.imageMovie)

        holder.constraintLayout.setOnClickListener {
            val intent = Intent(context, DetailsMoviesActivity::class.java)
            intent.putExtra("movieID", list?.id)
            context.startActivity(intent)
        }

    }

    inner class ListMovieAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageMovie = itemView.imageMovie
        var textMovie = itemView.textMovie
        var constraintLayout = itemView.constraintLayout
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<ResultListMovieResponse>() {
            override fun areItemsTheSame(oldItem: ResultListMovieResponse, newItem: ResultListMovieResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ResultListMovieResponse, newItem: ResultListMovieResponse): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

}