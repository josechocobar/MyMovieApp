package com.cuty.mymovieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.animations.MyAnimator
import com.cuty.mymovieapp.databinding.FragmentSplashBinding
import com.cuty.mymovieapp.presenter.SplashViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private val viewmodel: SplashViewModel by viewModels()
    lateinit var binding: FragmentSplashBinding
    lateinit var mLogo: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        mLogo = binding.mlogo
        val animator = MyAnimator()
        animator.totalAnimations(mLogo)
        lifecycleScope.launch {
            viewmodel.isLoading.catch {}
                .collect { it ->
                    if (!it) {
                        nextFragment()
                    }
                }
        }



    }

    private fun nextFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

}