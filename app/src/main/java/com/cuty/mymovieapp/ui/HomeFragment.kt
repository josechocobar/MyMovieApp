package com.cuty.mymovieapp.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.application.internetchecker.NetworkConnection
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.databinding.FragmentHomeBinding
import com.cuty.mymovieapp.presenter.MainViewModel
import com.cuty.mymovieapp.ui.recycler.PopularAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), PopularAdapter.OnMovieItemClickListener {

    private var binding: FragmentHomeBinding? = null
    val viewmodel: MainViewModel by viewModels()
    lateinit var mcontext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        mcontext = requireContext()
        observeInternet()
        setupSearchView(binding!!.svSearchMovie)
        setUpPopular(binding!!.buPopular, binding!!.buTopRated)
        setupTopRated(binding!!.buTopRated, binding!!.buPopular)
    }

    fun observeInternet() {
        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkConnection = NetworkConnection(connectivityManager)
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isConnected) {
                Log.d("INTERNET", "OK connection")
                lifecycle.coroutineScope.launch {
                    viewmodel.actualDb()
                    setupObserver()
                }
            } else {
                Log.d("INTERNET", "Disconnected")
            }
        }
    }

    override fun onMovieClick(item: Movie, position: Int) {
        Log.d(TAG, "Boton presionado $position")

        try {
            findNavController().navigate(
                R.id.action_homeFragment_to_movieFragment,
                bundleOf("id" to item.id))
        } catch (e: Exception) {
            Log.d(TAG, "Falla porque ${e.message}")
        }
    }

    private fun setupObserver() {
        viewmodel.viewModelScope.launch {
            viewmodel.getListOfMovies
                .collect { list ->
                    if (list.isNotEmpty()) {
                        setUpRecyclerView(list)
                    }
                }
        }
    }


    private fun setupSearchView(view: SearchView) {
        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String): Boolean {
                setUpNameObserver(name)
                setupObserver()
                return false
            }

            override fun onQueryTextChange(name: String): Boolean {
                setUpNameObserver(name)
                setupObserver()
                return false
            }
        })

    }

    private fun setUpNameObserver(name: String) {
        viewmodel.getMovieByName(name)
    }

    private fun setUpPopular(popularButton: ImageButton, topRatedButton: ImageButton) {
        popularButton.setOnClickListener {
            viewmodel.getPopularListOfMovies()
            setupObserver()
            //popularButton.isEnabled = false
            //topRatedButton.isEnabled = true
        }
    }

    private fun setupTopRated(topRatedButton: ImageButton, popularButton: ImageButton) {
        topRatedButton.setOnClickListener {
            viewmodel.getTopRatedMovieList()
            setupObserver()
            //topRatedButton.isEnabled = false
            //popularButton.isEnabled = true
        }
    }

    fun setUpRecyclerView(value: List<Movie>) {
        binding?.rvSuggestions?.adapter = PopularAdapter(
            requireContext(),
            value,
            this@HomeFragment
        )
        binding!!.rvSuggestions.layoutManager = LinearLayoutManager(requireContext())
    }

}