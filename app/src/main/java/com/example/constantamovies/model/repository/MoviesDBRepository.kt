package com.example.constantamovies.model.repository

import com.example.constantamovies.data.MoviesData
import retrofit2.Call

interface MoviesDBRepository {

    fun getMovies() : Call<MoviesData>
}