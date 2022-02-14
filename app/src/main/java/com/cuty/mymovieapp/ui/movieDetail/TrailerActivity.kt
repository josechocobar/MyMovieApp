package com.cuty.mymovieapp.ui.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cuty.mymovieapp.R
import com.cuty.mymovieapp.databinding.ActivityTrailerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback


class TrailerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTrailerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val key = intent?.extras?.getString("key") ?:"dQw4w9WgXcQ"
        playNow(key)
    }
    fun playNow(key: String){
        playVideo(key)
    }

    fun playVideo(key:String){
        binding.youtubeVideoView.getYouTubePlayerWhenReady(object: YouTubePlayerCallback {
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(key,0F)
            }
        } )
    }
}