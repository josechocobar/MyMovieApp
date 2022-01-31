package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video

class RemoteDataSource() :RemoteDataSourceInt {
    override suspend fun getPopularMovies() : MovieRequest {
        return RetrofitService.webService.getPopularMovies()
    }
    override suspend fun getTrailer(id:Int): Video {
        return RetrofitService.webService.getVideos(id)
    }

    override suspend fun getTopRated(): MovieRequest {
        return RetrofitService.webService.getTopRated()
    }
}