package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video

interface RemoteDataSourceInt {
    suspend fun getPopularMovies() : MovieRequest
    suspend fun getTrailer(id:Int): Video
}