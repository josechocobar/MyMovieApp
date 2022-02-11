package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.Credits
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET("popular?")
    suspend fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): MovieRequest

    @GET("{id}/videos?")
    suspend fun getVideos(
        @Path("id") id: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Video

    @GET("top_rated?")
    suspend fun getTopRated(
        @Query("api_key") key: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): MovieRequest

    @GET("/movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") id:Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ):Credits
}