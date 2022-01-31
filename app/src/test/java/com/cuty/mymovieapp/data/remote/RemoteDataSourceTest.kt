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

    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup(){
        print("setup lauched")
        remoteDataSource = RemoteDataSource()
    }

    @Test
    fun getPopular(){
        //print(request)
        runBlocking {
            val request = remoteDataSource.getPopularMovies()
            print(request)
            Assert.assertNotNull(request)
        }

    }
    @Test
    fun getVideos(){
        runBlocking {
            val request = remoteDataSource.getTrailer(634649)
            print(request)
            Assert.assertNotNull(request)
        }
    }
    @Test
    fun dontGetTRailers(){
        runBlocking {
            val request = remoteDataSource.getTrailer(730154)
            print(request)
            Assert.assertNull(request)
        }
    }

    @Test
    fun getTopRated(){
        runBlocking {
            val request = remoteDataSource.getTopRated()
            print(request)
            Assert.assertNotNull(request)
        }
    }

}