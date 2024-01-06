package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.utils.Constants.API_KEY
import com.cuty.mymovieapp.utils.Constants.LANG_ENG
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTest{

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var key:String
    private lateinit var lang:String
    private var page:Int = 1

    @Before
    fun setup(){
        print("setup launched")
        remoteDataSource = RemoteDataSource()
        key = API_KEY
        lang = LANG_ENG
    }

    @Test
    fun getPopular(){
        //print(request)
        runBlocking {
            val request = remoteDataSource.getPopularMovies(key,lang,page)
            print(request)
            Assert.assertNotNull(request)
        }

    }
    @Test
    fun getNullorError(){
        runBlocking {
            val request = remoteDataSource.getPopularMovies("one",lang,page)
            print(request)
            Assert.assertNotNull(request)
            print(request.message)
        }
    }
    @Test
    fun getVideos(){
        runBlocking {
            val request = remoteDataSource.getTrailer(634649,key,lang)
            print(request)
            Assert.assertNotNull(request)
        }
    }

    @Test
    fun getTopRated(){
        runBlocking {
            val request = remoteDataSource.getTopRated(key,lang,page)
            print(request)
            Assert.assertNotNull(request)
        }
    }

    @Test
    fun getCast(){
        runBlocking {
            val request = remoteDataSource.getCredits(634649)
            println(request.cast[3])
            println(request.crew[0])
            Assert.assertNotNull(request)
        }
    }

}