package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video

class RemoteDataSource() :RemoteDataSourceInt {
    override suspend fun getPopularMovies(key:String,lang:String,page:Int) : MovieRequest {
        return RetrofitService.webService.getPopularMovies(key,lang,page)
    }
    override suspend fun getTrailer(id:Int,key:String,lang:String): Video {
        return RetrofitService.webService.getVideos(id,key,lang)
    }

    override suspend fun getTopRated(key:String,lang:String,page:Int): MovieRequest {
        return RetrofitService.webService.getTopRated(key,lang,page)
    }
}