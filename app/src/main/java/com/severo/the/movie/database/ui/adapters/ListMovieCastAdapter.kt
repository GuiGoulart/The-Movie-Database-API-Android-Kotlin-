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
import com.severo.the.movie.database.api.response.CastListMovieCastDetailsResponse
import com.severo.the.movie.database.api.response.ListMovieCastDetailsResponse
import com.severo.the.movie.database.api.response.ResultListMovieResponse
import com.severo.the.movie.database.ui.activities.DetailsMoviesActivity
import com.severo.the.movie.database.ui.activities.ServerErrorActivity
import com.severo.the.movie.database.util.Util
import kotlinx.android.synthetic.main.rv_list_movie.view.*

class ListMovieCastAdapter() : RecyclerView.Adapter<ListMovieCastAdapter.ListMovieCastViewHolder>() {

    private var list: List<CastListMovieCastDetailsResponse>? = null
    private lateinit var context: Context

    fun setcontext(context: Context?) {
        this.context = context!!
    }

    fun listMovieCastAdapter(list: List<CastListMovieCastDetailsResponse>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ListMovieCastViewHolder {
        return ListMovieCastViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_list_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (list == null) {
            0
        } else list!!.size
    }

    override fun onBindViewHolder(holder: ListMovieCastViewHolder, position: Int) {
        val list = list!![position]

        holder.textMovie.text = "${list.character}\n${list.name}"
        Glide.with(context).load("http://image.tmdb.org/t/p/w500${list.profile_path}").into(holder.imageMovie)
        Util.loadImage(context, "http://image.tmdb.org/t/p/w500${list.profile_path}", holder.imageMovie)

    }

    inner class ListMovieCastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageMovie = itemView.imageMovie
        var textMovie = itemView.textMovie
    }

}