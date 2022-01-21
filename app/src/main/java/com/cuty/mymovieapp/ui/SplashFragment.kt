package com.cuty.mymovieapp.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.databinding.FragmentSplashBinding
import com.cuty.mymovieapp.presenter.SplashViewModel


class SplashFragment : Fragment() {
    private val viewmodel : SplashViewModel by viewModels()
    lateinit var binding: FragmentSplashBinding
    lateinit var mLogo : ImageView
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
        translater()
        rotater()
        scaler()

    }
    private fun translater(){
        val animator = ObjectAnimator.ofFloat(mLogo,View.TRANSLATION_Y,300f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }
    private fun rotater() {
        val animator  = ObjectAnimator.ofFloat(mLogo, View.ROTATION,-720f,0f)
        animator.duration = 1000L
        animator.start()
    }
    private fun scaler() {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,0.3f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,0.3f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(
            mLogo,scaleX,scaleY
        )
        animator.duration = 1000L
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }
    private fun nextFragment(){
        //findNavController().navigate()
    }

}