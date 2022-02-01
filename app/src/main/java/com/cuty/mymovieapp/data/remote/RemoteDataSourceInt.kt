package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video

interface RemoteDataSourceInt {
    suspend fun getPopularMovies(key:String,lang:String,page:Int) : MovieRequest
    suspend fun getTrailer(id:Int,key:String,lang:String): Video
    suspend fun getTopRated(key:String,lang:String,page:Int):MovieRequest
}