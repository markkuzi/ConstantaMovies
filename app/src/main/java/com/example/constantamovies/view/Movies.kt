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
import com.example.constantamovies.viewmodel.MoviesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Movies : Fragment() {

    private var binding : MoviesBinding? = null
    private var movieAdapter: MovieAdapter? = null
    private val moviesViewModel : MoviesViewModel = MoviesViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.movies, container, false)
        moviesViewModel.getMovies()
        initObservers()
        initRecyclerView()

        return binding?.root
    }

    private fun initRecyclerView(){
        binding?.mMovieList?.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter = MovieAdapter({movieModel: MovieModel->action(movieModel)}) //
        binding?.mMovieList?.adapter = movieAdapter
    }

    private fun initObservers(){
        moviesViewModel.apply {
            movies.observe(requireActivity()){
                movieAdapter?.setList(it)
                movieAdapter?.notifyDataSetChanged()

            }
        }
    }

    private fun action(movieModel: MovieModel){

        val detailsMovie = MovieDetails()
        val parameters = Bundle()
        parameters.putString("title", movieModel.title)

        detailsMovie.arguments = parameters
        detailsMovie.show(parentFragmentManager, "details")
    }
}