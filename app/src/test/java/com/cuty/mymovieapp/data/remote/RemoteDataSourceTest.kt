package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.MovieRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTest{


    @Before
    fun setup(){
        print("setup lauched")
    }

    @Test
    fun getTrailers(){
        //print(request)
        runBlocking {
            val request = RetrofitService.webService.getPopularMovies()
            print(request)
            Assert.assertNotNull(request)
        }

    }
    @Test
    fun getVideos(){
        runBlocking {
            val request = RetrofitService.webService.getVideos(634649)
            print(request)
            Assert.assertNotNull(request)
        }
    }

}