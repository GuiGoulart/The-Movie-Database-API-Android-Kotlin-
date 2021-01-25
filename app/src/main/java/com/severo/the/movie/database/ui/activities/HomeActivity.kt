package com.severo.the.movie.database.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.severo.the.movie.database.R
import com.severo.the.movie.database.api.data.State
import com.severo.the.movie.database.ui.adapters.ListMovieAdapter
import com.severo.the.movie.database.viewmodel.ListMovieViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var clientViewModel: ListMovieViewModel
    private lateinit var listMovieAdapter: ListMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        listingMovies()

    }

    private fun listingMovies(){
        clientViewModel = ViewModelProvider(this).get(ListMovieViewModel::class.java)

        listMovieAdapter = ListMovieAdapter { clientViewModel.retry() }
        listMovieAdapter.setcontext(this)

        clientViewModel.newsList.observe(this, {
                if(it.size > 0) {
                    listMovieAdapter.submitList(it)
                    rvListMovie.adapter = listMovieAdapter
                    rvListMovie?.layoutManager = GridLayoutManager(this, 2)
                }
            })

        clientViewModel.getState().observe(this, { state ->
            if (!clientViewModel.listIsEmpty()) {
                listMovieAdapter.setState(state ?: State.DONE)
            }
        })

    }

}