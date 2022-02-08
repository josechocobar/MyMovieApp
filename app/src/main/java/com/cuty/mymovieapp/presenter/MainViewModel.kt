package com.cuty.mymovieapp.presenter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.cuty.mymovieapp.data.domain.RepoImplementation
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.Trailer
import com.cuty.mymovieapp.data.models.Video
import com.cuty.mymovieapp.data.remote.TimeController.TimeControl
import com.cuty.mymovieapp.data.remote.cases.Cases
import com.cuty.mymovieapp.utils.Constants.API_KEY
import com.cuty.mymovieapp.utils.Constants.LANG_ENG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.lang.Exception
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation,private var time:TimeControl
) : ViewModel() {

    lateinit var getListOfMovies: Flow<List<Movie>>
    init {
        time.setTimecontrol()
        getAllMovies()
    }
    fun getTopRatedMovieList() {
        getListOfMovies = repoImplementation.getTopRatedMovieList(true)
    }

    fun getPopularListOfMovies() {
        getListOfMovies = repoImplementation.getPopularMovieList(true)
    }

    fun getAllMovies() {
        getListOfMovies = repoImplementation.getMovieLocalList()
    }

    fun getMovieByName(name: String) {
        getListOfMovies = if (name == "") {
            repoImplementation.getMovieLocalList()
        } else {
            repoImplementation.getMovieByTitle(name)
        }
    }
    suspend fun getVideos(movie: Movie):Video{
        val video = repoImplementation.remoteDataSource.getTrailer(movie.id,API_KEY, LANG_ENG)
        return video
    }
    suspend fun getAllTrailers(movie: Movie): List<Trailer>{
        val videoRequest = getVideos(movie)
        val trailerList = mutableListOf<Trailer>()
        if (videoRequest.results.isNullOrEmpty()){
            return listOf(Trailer(key = "dQw4w9WgXcQ",site = "youtube.com",id = "asdasd",iso6391 = "asdasd",iso31661 = "asdasd",name = "asdasd",official = false,publishedAt = "asdasd",type = "rick",size = 100))
        }
        videoRequest.results?.forEach {
            trailerList.add(it)
            Log.d("MOVIE","get 1 trailer called ${it.key}")
        }
        return trailerList
    }



    suspend fun actualDb() {
        if (!time.setFirstDb){
            repoImplementation.localDataSource.deleteAll()
            actualizePopular()
            delay(200)
            actualizeTopRated()
            time.setFirstDb = true
        }
        if (time.decideTimeControl(LocalTime.now())){
            repoImplementation.localDataSource.deleteAll()
            actualizePopular()
            actualizeTopRated()
        }
        else{
            Log.d("MOVIES","La base de datos ya está actualizada")
        }

    }

    suspend fun actualizePopular() {
        for (i in 1..5) {
            val fetchPopularMovieData = try {
                repoImplementation.remoteDataSource.getPopularMovies(API_KEY, LANG_ENG, i)
            } catch (e: Exception) {
                throw Exception("remote repo error", e)
            }
            fetchPopularMovieData.results.let { list ->
                list?.forEach { movie ->
                    movie.popular = true
                    repoImplementation.insertMovie(movie)
                } ?: throw Exception("no results")
            }
            Log.d(ContentValues.TAG, "upgraded db")
        }
    }

    suspend fun actualizeTopRated() {
        for (i in 1..5) {
            val fetchTopRated = try {
                repoImplementation.getTopRated(API_KEY, LANG_ENG, i)
            } catch (e: Exception) {
                throw Exception("remote repo error", e)
            }
            fetchTopRated.results?.forEach { movie ->
                val exist = repoImplementation.existThisMovie(movie.original_title)
                if (!exist) {
                    movie.topRated = true
                    repoImplementation.insertMovie(movie)
                    Log.d("MOVIE","Inserted new toprated")
                } else {
                    val eMovie = repoImplementation.getMovieByOriginalTitle(movie.original_title)
                    eMovie.topRated = true
                    repoImplementation.insertMovie(eMovie)
                    Log.d("MOVIE","Changed new toprated")
                }

            } ?: throw Exception("no results")
        }
        Log.d(ContentValues.TAG, "upgraded db")
    }

}