package com.cuty.mymovieapp.data.domain

import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video
import kotlinx.coroutines.flow.Flow

interface IRepo {
    suspend fun getMovieList(key:String,lang:String,page:Int) : MovieRequest
    fun getMovieLocalList() : Flow<List<Movie>>
    fun getPopularMovieList(type:Boolean):Flow<List<Movie>>
    fun getTopRatedMovieList(type:Boolean):Flow<List<Movie>>
    suspend fun getMovieById(idroom:Int):Movie
    suspend fun getTrailer(id:Int,key:String,lang:String): Video
    suspend fun getTopRated(key:String,lang:String,page:Int) : MovieRequest
    fun getMovieByTitle(name:String):Flow<List<Movie>>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
    suspend fun deleteAll()
    suspend fun existThisMovie(name: String):Boolean
    suspend fun getMovieByOriginalTitle(name:String):Movie
}