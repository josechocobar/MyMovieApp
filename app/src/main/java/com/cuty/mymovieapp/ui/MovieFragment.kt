package com.cuty.mymovieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.databinding.FragmentMovieBinding
import com.cuty.mymovieapp.presenter.MainViewModel


class MovieFragment : Fragment() {
    private var binding : FragmentMovieBinding?=null
    val viewmodel:MainViewModel by viewModels()
    var movie : Movie?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bundle -> movie = bundle.getParcelable<Movie>("movie")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
    private fun setData(){

    }
}