package com.cuty.mymovieapp.data.domain

import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.TopRated
import com.cuty.mymovieapp.data.models.Video
import kotlinx.coroutines.flow.Flow

interface IRepo {
    suspend fun getMovieList(key:String,lang:String,page:Int) : MovieRequest
    suspend fun getMovieLocalList() : Flow<List<Movie>>
    suspend fun getMovieById(idroom:Int):Movie
    suspend fun getTrailer(id:Int,key:String,lang:String): Video
    suspend fun getTopRated(key:String,lang:String,page:Int) : MovieRequest
    suspend fun getLocalTopRated(): Flow<List<Movie>>
}