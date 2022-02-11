package com.cuty.mymovieapp.data.remote

import com.cuty.mymovieapp.data.models.Credits
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video
import com.cuty.mymovieapp.utils.Constants.API_KEY
import com.cuty.mymovieapp.utils.Constants.LANG_ENG

class RemoteDataSource() : RemoteDataSourceInt {
    override suspend fun getPopularMovies(key: String, lang: String, page: Int): MovieRequest {
        return RetrofitService.webService.getPopularMovies(key, lang, page)
    }

    override suspend fun getTrailer(id: Int, key: String, lang: String): Video {
        return RetrofitService.webService.getVideos(id, key, lang)
    }

    override suspend fun getTopRated(key: String, lang: String, page: Int): MovieRequest {
        return RetrofitService.webService.getTopRated(key, lang, page)
    }

    override suspend fun getCredits(id: Int): Credits {
        return RetrofitService.webService.getCredits(id,API_KEY, LANG_ENG)
    }
}