package com.example.constantamovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArraySet
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.constantamovies.data.MovieData
import com.example.constantamovies.data.MovieModel
import com.example.constantamovies.databinding.MovieItemBinding

class MovieAdapter (private val action:(MovieModel)->Unit):
    RecyclerView.Adapter<ViewHolder>() {

    private val movieList = ArrayList<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MovieItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.movie_item, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position], action)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setList(products: List<MovieData?>){
        movieList.clear()
        for (i in products.indices){
            val title = products[i]?.title.toString()
            val directorName = products[i]?.directorName.toString()
            val releaseYear = products[i]?.releaseYear!!.toInt()

            val actorsList:MutableSet<String> = ArraySet()

            for (actorName in 0 until products[i]?.actors!!.size) {
                val actor = products[i]!!.actors[actorName].actorName
                actorsList.add(actor)
            }
            val actors = actorsList.toString()
            val userDetailsTest =
                MovieModel(title, directorName, releaseYear, actors)
            movieList.add(userDetailsTest)
        }
        movieList.sortBy { it.releaseYear }
    }
}

class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieModel: MovieModel, action: (MovieModel) -> Unit) {
        binding.mTitle.text = movieModel.title

        val directorNameSet = movieModel.directorName.split(" ")
        val directorName = directorNameSet[2] + " " + directorNameSet[0][0] + "." + directorNameSet[1][0] + "."

        binding.mDirectorName.text = directorName
        binding.mReleaseYear.text = "(" + movieModel.releaseYear.toString() + ")"
        binding.mActors.text = movieModel.actors.replace("{","").replace("}","")

        binding.mCard.setOnClickListener(View.OnClickListener {
            action(movieModel)
        })


    }
}