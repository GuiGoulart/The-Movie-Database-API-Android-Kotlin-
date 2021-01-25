package com.severo.the.movie.database.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.severo.the.movie.database.R
import com.severo.the.movie.database.ui.adapters.ListMovieAdapter
import com.severo.the.movie.database.ui.adapters.ListMovieCastAdapter
import com.severo.the.movie.database.util.Util
import com.severo.the.movie.database.util.Util.convertDateFormat
import com.severo.the.movie.database.viewmodel.ListMovieCastDetailsViewModel
import com.severo.the.movie.database.viewmodel.ListMovieDetailsViewModel
import kotlinx.android.synthetic.main.activity_details_movies.*

class DetailsMoviesActivity : AppCompatActivity() {

    private lateinit var listMovieDetailsViewModel: ListMovieDetailsViewModel
    private lateinit var listMovieCastDetailsViewModel: ListMovieCastDetailsViewModel
    private lateinit var listMovieCastAdapter: ListMovieCastAdapter

    private var movieID: Int? = 0
    private var sizeGenre: Int? = 0
    private var sizeGenreMutable: Int? = 0
    private var genresText: String? = "Gênero: "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_movies)

        toolbar.setNavigationOnClickListener { onBackPressed() }

        val bundle = intent.extras
        movieID = bundle?.getInt("movieID")

        listingMovies()

    }

    @SuppressLint("SetTextI18n")
    private fun listingMovies(){

        listMovieCastDetailsViewModel = ViewModelProvider(this).get(ListMovieCastDetailsViewModel::class.java)

        listMovieCastAdapter = ListMovieCastAdapter()
        listMovieCastAdapter.setcontext(this)

        rvCast?.adapter = listMovieCastAdapter
        rvCast?.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        listMovieCastDetailsViewModel.listMovieCastDetails(this, movieID).observe(this, {
            if (it != null) {
                listMovieCastAdapter.listMovieCastAdapter(it.cast)
            }
        })

        listMovieDetailsViewModel = ViewModelProvider(this).get(ListMovieDetailsViewModel::class.java)
        listMovieDetailsViewModel.listMovieDetails(this, movieID).observe(this, {
            if (it != null) {
                Glide.with(this).load("http://image.tmdb.org/t/p/w500${it.poster_path}").into(imageLogo)
                Util.loadImage(this, "http://image.tmdb.org/t/p/w500${it.poster_path}", imageLogo)
                toolbarTitle.text = it.title
                textViewerRating.text = "Avaliação: ${it.vote_average.toString()}"
                textReleaseDateOf.text = "Data de lançamento: ${
                    convertDateFormat(
                        it.release_date,
                        "yyyy-MM-dd",
                        "dd/MM/yyyy"
                    )
                }"
                textResume.text = it.overview

                sizeGenre = it.genres?.size

                for (genre in it.genres!!) {

                    sizeGenreMutable = sizeGenreMutable?.plus(1)

                    if (sizeGenreMutable != sizeGenre) {
                        genresText += "${genre.name}, "
                        textMovieGenre.text = genresText
                    } else {
                        genresText += "${genre.name}"
                        textMovieGenre.text = genresText
                    }

                }
            }

        })

    }

}