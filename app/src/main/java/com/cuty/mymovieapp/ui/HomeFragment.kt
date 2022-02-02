package com.cuty.mymovieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.databinding.FragmentHomeBinding
import com.cuty.mymovieapp.ui.recycler.PopularAdapter


class HomeFragment : Fragment(), PopularAdapter.OnMovieItemClickListener {

    private var binding: FragmentHomeBinding? = null
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

    override fun onMovieClick(item: Movie, position: Int) {
        Log.d("CLICK", "CLICK")
    }

    private fun setUpRecyclerView() {
        binding?.rvPopular?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvPopular?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }
}