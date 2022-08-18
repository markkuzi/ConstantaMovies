package com.example.constantamovies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.constantamovies.data.MovieData
import com.example.constantamovies.data.MoviesData
import com.example.constantamovies.model.repository.MoviesDBRepository
import com.example.constantamovies.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel {

    private val _movies = MutableLiveData<List<MovieData?>>()
    val movies: LiveData<List<MovieData?>> = _movies

    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    fun getMovies() {
        val response = mMoviesRepository.getMovies()
        response.enqueue(object : Callback<MoviesData> {
            override fun onResponse(call: Call<MoviesData>, response: Response<MoviesData>) {
                Log.d("RetrofitLogs", "OnResponse Success ${call.toString()}")
                _movies.postValue(response.body()?.items)
            }

            override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                Log.d("RetrofitLogs", "OnFailure ${t.message}")
            }


        })
    }
}