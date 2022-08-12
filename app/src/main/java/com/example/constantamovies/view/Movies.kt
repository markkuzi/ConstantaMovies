package com.example.constantamovies.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArraySet
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.constantamovies.model.apis.ApiInterface
import com.example.constantamovies.MovieAdapter
import com.example.constantamovies.R
import com.example.constantamovies.data.MovieModel
import com.example.constantamovies.data.MoviesData
import com.example.constantamovies.databinding.MoviesBinding
import com.example.constantamovies.model.repository.MoviesDBRepository
import com.example.constantamovies.model.repository.MoviesDBRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Movies : Fragment() {

    private var binding: MoviesBinding? = null
    private var movieAdapter: MovieAdapter? = null
    private val mMoviesRepository: MoviesDBRepository = MoviesDBRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.movies, container, false)

        getMovies()

        return binding?.root
    }

    private fun getMovies(){

        val response = mMoviesRepository.getMovies()

        response.enqueue( object : Callback<MoviesData> {
            override fun onResponse(call: Call<MoviesData>?, response: Response<MoviesData>?) {
                Log.d("TestLogs", "OnResponseSuccess ${response?.body()?.items}")
                val moviesList: ArrayList<MovieModel> = ArrayList()

                for (i in 0 until response?.body()?.items?.size!!){
                    val items = response.body()?.items
                    val title = items?.get(i)?.title.toString()
                    val directorName = items?.get(i)?.directorName.toString()
                    val releaseYear = items?.get(i)?.releaseYear?.toInt()!!

                    val actorSet:MutableSet<String> = ArraySet()

                    for (name in 0 until items[i].actors.size) {
                        val actor = items[i].actors[name].actorName
                        actorSet.add(actor)
                    }
                    val actors = actorSet.toString()

                    val movesDetails =
                        MovieModel(title, directorName, releaseYear, actors)
                    moviesList.add(movesDetails)
                }

                moviesList.sortBy { it.releaseYear }

                binding?.mMovieList?.layoutManager = LinearLayoutManager(context)
                movieAdapter = MovieAdapter({userModel: MovieModel->action(userModel)}, moviesList)
                binding?.mMovieList?.adapter = movieAdapter

            }

            override fun onFailure(call: Call<MoviesData>?, t: Throwable?) {
                Log.d("TestLogs", "OnFailure: $t")

            }
        })}

    private fun action(movieModel: MovieModel){

        val detailsMovie = MovieDetails()
        val parameters = Bundle()
        parameters.putString("title", movieModel.title)

        detailsMovie.arguments = parameters
        detailsMovie.show(parentFragmentManager, "details")
    }
}