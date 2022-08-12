package com.example.constantamovies.model.apis

import com.example.constantamovies.data.MoviesData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("constanta-android-dev/intership-wellcome-task/main/films.json")
    fun getMovies() : Call<MoviesData>

    companion object {

        var BASE_URL = "https://raw.githubusercontent.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}