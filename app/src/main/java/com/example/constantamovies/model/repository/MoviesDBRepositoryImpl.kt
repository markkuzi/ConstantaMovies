package com.example.constantamovies.model.repository

import com.example.constantamovies.data.MoviesData
import com.example.constantamovies.model.apis.ApiInterface
import retrofit2.Call

class MoviesDBRepositoryImpl : MoviesDBRepository {

    private val apiInterface = ApiInterface.create()

    override fun getMovies(): Call<MoviesData> {
        return apiInterface.getMovies()
    }
}