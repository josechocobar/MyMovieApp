package com.cuty.mymovieapp.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.data.models.Cast
import com.cuty.mymovieapp.data.models.Crew
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.Trailer
import com.cuty.mymovieapp.databinding.FragmentMovieBinding
import com.cuty.mymovieapp.presenter.MovieViewModel
import com.cuty.mymovieapp.ui.movieDetail.CastAdapter
import com.cuty.mymovieapp.ui.movieDetail.TrailersAdapter
import com.cuty.mymovieapp.utils.Constants
import com.cuty.mymovieapp.utils.URLType
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MovieFragment : Fragment(),TrailersAdapter.OnTrailerClickListener,CastAdapter.OnTrailerClickListener {
    private lateinit var binding : FragmentMovieBinding
    val viewmodel:MovieViewModel by viewModels()
    var movie : Movie?=null
    lateinit var urlType : URLType
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        setData()
        setupObserver()

    }
    fun setData(){
        movie?.let {
            viewmodel.setMovieData(it.id)
            setupMovieData(it)
        }
    }
    fun setupObserver(){
        viewmodel.viewModelScope.launch {
            viewmodel.listOfTrailers.collect {
                list->
                list.isNotEmpty().let {
                    setupTrailerRecyclerView(list)
                }
            }
            viewmodel.listOfCast.collect {
                cast-> val list = cast.subList(0,3)
                cast.isNotEmpty().let {
                    setupPersonsRecyclerView(list)
                }
            }
        }
    }
    fun setupTrailerRecyclerView(list: List<Trailer>) {
        binding.rvTrailers.adapter = TrailersAdapter(
            requireContext(),
            list,
            this
        )
        binding.rvTrailers.layoutManager = LinearLayoutManager(requireContext())
    }
    fun setupMovieData(movie:Movie){
        binding.tvName.text = movie.title
        Glide.with(requireContext()).load("${Constants.IMG_URL}${movie.poster_path}").transform(RoundedCorners(200)).centerCrop().into(binding.ivFrontPage)
    }
    fun setupPersonsRecyclerView(listOfCast: List<Cast>){
        binding.rvActors.adapter = CastAdapter(
            requireContext(),
            listOfCast,
            this
        )
    }

    override fun onCastClick(actor: Cast, position: Int) {
        Log.d(TAG,"OnCast Clicker")
    }

    override fun onTrailerClick(trailer: Trailer, position: Int) {
        try {
            findNavController().navigate(
                R.id.trailerActivity, bundleOf("key" to trailer.key))
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Falla porque ${e.message}")
        }
    }
}