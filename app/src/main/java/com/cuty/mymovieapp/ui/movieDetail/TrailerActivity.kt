package com.cuty.mymovieapp.ui.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.coroutineScope
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.databinding.ActivityTrailerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrailerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTrailerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_trailer)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setScreenOn()
        playNow()
    }
    fun playNow(){
        playVideo("dQw4w9WgXcQ")
    }

    fun playVideo(key:String){
        binding.youtubeVideoView.getYouTubePlayerWhenReady(object: YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(key,0F)
            }
        } )
    }
    private fun setScreenOn(){
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}