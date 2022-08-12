package com.example.constantamovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.constantamovies.R
import com.example.constantamovies.databinding.MovieDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MovieDetails : BottomSheetDialogFragment() {

    private var binding : MovieDetailsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details, container, false)

        binding?.mCardTitle?.text = arguments?.getString("title").toString()


        return binding?.root
    }


}