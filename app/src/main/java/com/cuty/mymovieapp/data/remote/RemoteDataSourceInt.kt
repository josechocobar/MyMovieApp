package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.Credits
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video
import com.cuty.mymovieapp.utils.Resource

interface RemoteDataSourceInt {
    suspend fun getPopularMovies(key: String, lang: String, page: Int): Resource<MovieRequest>
    suspend fun getTrailer(id: Int, key: String, lang: String): Video
    suspend fun getTopRated(key: String, lang: String, page: Int): MovieRequest

    /*
    get Credits from remote database with id number
     */
    suspend fun getCredits(id: Int): Credits
}