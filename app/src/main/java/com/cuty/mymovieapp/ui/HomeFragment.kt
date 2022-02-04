package com.cuty.mymovieapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.application.internetchecker.NetworkConnection
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.databinding.FragmentHomeBinding
import com.cuty.mymovieapp.presenter.MainViewModel
import com.cuty.mymovieapp.ui.recycler.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), PopularAdapter.OnMovieItemClickListener {

    private var binding: FragmentHomeBinding? = null
    val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeInternet()
        setupObserver()
    }

    fun observeInternet() {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkConnection = NetworkConnection(connectivityManager)
        networkConnection.observe(viewLifecycleOwner, { isConnected ->
            if (isConnected) {
                Log.d("INTERNET", "OK connection")
            } else {
                Log.d("INTERNET", "Disconnected")
            }
        })
    }

    override fun onMovieClick(item: Movie, position: Int) {
        Log.d("CLICK", "CLICK")
    }
    private fun setupObserver(){
        viewmodel.viewModelScope.launch {
            viewmodel.getListOfMovies.catch {  }
                .map {
                    setUpRecyclerView(it)
                }
                .collect()
        }
    }

    private fun setupSearchView(view: SearchView) {
        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String): Boolean {
                setUpNameObserver(name)
                return false
            }

            override fun onQueryTextChange(name: String): Boolean {
                setUpNameObserver(name)
                return false
            }
        })

    }

    private fun setUpNameObserver(name: String) {
        viewmodel.getMovieByName(name)
    }

    private fun setUpPopular() {
        viewmodel.getPopularListOfMovies()
    }
    private fun setupTopRated(){
        viewmodel.getTopRatedMovieList()
    }

    fun setUpRecyclerView(value: List<Movie>) {
        binding?.rvSuggestions?.adapter = PopularAdapter(
            requireContext(),
            value,
            this@HomeFragment
        )
    }

}