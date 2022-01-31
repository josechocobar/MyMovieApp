package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {

    @GET("popular?api_key=0a8956c5ce74c43236f60c0fe039e3c1&language=en-US&page=1")
    suspend fun getPopularMovies():MovieRequest

    //@GET("/3/movie/")
    //suspend fun getPopulars(@Query(value = "$API_KEY"))
    @GET("{id}/videos?api_key=0a8956c5ce74c43236f60c0fe039e3c1&language=en-U")
    suspend fun getVideos(@Path("id")id:Int): Video
}