package com.cuty.mymovieapp.ui

import android.content.ContentValues
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.data.models.Cast
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.Trailer
import com.cuty.mymovieapp.databinding.FragmentMovieBinding
import com.cuty.mymovieapp.presenter.MovieViewModel
import com.cuty.mymovieapp.ui.movieDetail.CastAdapter
import com.cuty.mymovieapp.ui.movieDetail.TrailersAdapter
import com.cuty.mymovieapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MovieFragment : Fragment(), TrailersAdapter.OnTrailerClickListener,
    CastAdapter.OnTrailerClickListener {
    lateinit var binding: FragmentMovieBinding
    val viewmodel: MovieViewModel by viewModels()

    var idmovie: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            idmovie = bundle.getInt("id")
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
        castObserver()
    }

    fun setData() {
        viewmodel.viewModelScope.launch {
            idmovie?.let {
                viewmodel.getMovie(it)
                viewmodel.movie.collect { value ->
                    setupMovieData(value)
                }
            }
        }
    }

    fun castObserver() {
        lifecycle.coroutineScope.launch {
            idmovie?.let {
                viewmodel.getCast(it).collect { value ->
                    setupPersonsRecyclerView(value)
                }
            }

        }
    }

    fun setupObserver() {
        lifecycle.coroutineScope.launch {
            idmovie?.let {
                viewmodel.getTrailers(it).collect { value ->
                    setupTrailerRecyclerView(value)
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
        val decoration =
            DividerItemDecoration(requireContext(),
                HORIZONTAL)
        binding.rvTrailers.addItemDecoration(decoration)
    }

    fun setupMovieData(movie: Movie) {
        binding.tvName.text = movie.title
        Glide.with(requireContext()).load("${Constants.IMG_URL}${movie.poster_path}")
            .transform(RoundedCorners(200)).centerCrop().into(binding.ivFrontPage)
    }

    fun setupPersonsRecyclerView(listOfCast: List<Cast>) {
        binding.rvActors.adapter = CastAdapter(
            requireContext(),
            listOfCast,
            this
        )
        binding.rvActors.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCastClick(actor: Cast, position: Int) {
        Toast.makeText(requireContext(), "Hola caracolacast!", Toast.LENGTH_LONG).show()

        try {
            findNavController().navigate(R.id.trailerActivity)
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Falla porque ${e.message}")
        }
    }

    override fun onTrailerClick(trailer: Trailer, position: Int) {
        Log.d(ContentValues.TAG, "Falla porque $position")
        Toast.makeText(requireContext(), "Hola caracola! trailer", Toast.LENGTH_LONG).show()

        try {
            findNavController().navigate(R.id.trailerActivity,
                bundleOf("key" to trailer.key))
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Falla porque ${e.message}")
        }
    }
}