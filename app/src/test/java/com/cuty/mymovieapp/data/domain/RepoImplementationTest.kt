package com.cuty.mymovieapp.data.domain

import com.cuty.mymovieapp.data.local.AppDatabase
import com.cuty.mymovieapp.data.local.LocalDataSource
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.remote.RemoteDataSource
import com.cuty.mymovieapp.utils.Constants
import com.cuty.mymovieapp.utils.Resource
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepoImplementationTest{

    @io.mockk.impl.annotations.MockK
    var repoImplementation: RepoImplementation = mockk()
    private lateinit var trueRepoImplementation : RepoImplementation
    private var remoteDataSource = RemoteDataSource()
    @io.mockk.impl.annotations.MockK
    private var localDataSource = LocalDataSource(mockk())
    @io.mockk.impl.annotations.MockK
    private lateinit var movie1 : Movie
    @io.mockk.impl.annotations.MockK
    private lateinit var movie2 : Movie
    @io.mockk.impl.annotations.MockK
    var movieRequest: MovieRequest= mockk()
    private lateinit var key:String
    private lateinit var lang:String
    private var page:Int = 1
    //private lateinit var myRequest:


    @Before
    fun setUp() {
        key = Constants.API_KEY
        lang = Constants.LANG_ENG
        coEvery { repoImplementation.getMovieList(key, lang, page) } returns Resource.Success(movieRequest)
        coEvery { repoImplementation.getMovieList(key,lang,page) } returns Resource.Error(message = "HTTP 404 ", data = null)
        trueRepoImplementation = RepoImplementation(remoteDataSource,localDataSource)
        //coEvery { remoteDataSource.getPopularMovies(key,lang,page) } returns Resource.Success(movieRequest)
    }
    @Test
    fun getRemoteDataSuccess(){
        runBlocking {
            val myRequest = repoImplementation.getMovieList(key, lang, page)
            println(myRequest.data)
        }


    }
    @Test
    fun getRemoteDataError(){
        runBlocking {
            val myRequest = repoImplementation.getMovieList(key, lang, page)
            println(myRequest.message)
        }

    }
    @Test
    fun getRemoteBadKey(){
        runBlocking {
            val myRequest = trueRepoImplementation.getMovieList("1", lang, page)
            println(myRequest.message)
            Assert.assertEquals("Error unauthorized: ${myRequest.message}","HTTP 401 ",myRequest.message)
        }
    }
    @Test
    fun getRemoteDataNull(){
        runBlocking {
            val myRequest = trueRepoImplementation.getMovieList("1", lang, page)
            println(myRequest.message)
            Assert.assertEquals("Error data: ${myRequest.data}",null,myRequest.data)
        }
    }
    @Test
    fun getRemoteNoInternet(){
        runBlocking {
            val myRequest = trueRepoImplementation.getMovieList(key, lang, page)
            println(myRequest.message)
            Assert.assertEquals("Error no internet: ${myRequest.data}",null,myRequest.data)
        }
    }
}