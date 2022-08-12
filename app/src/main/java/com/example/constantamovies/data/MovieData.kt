package com.example.constantamovies.data


data class MovieData(
                     val actors: List<ActorsData>,
                     val directorName: String,
                     val releaseYear: Int,
                     val title: String
)
