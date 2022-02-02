package com.cuty.mymovieapp.data.domain

import com.cuty.mymovieapp.data.local.LocalDatabaseDao
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.TopRated
import com.cuty.mymovieapp.data.models.Video
import com.cuty.mymovieapp.data.remote.RemoteDataSourceInt
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoImplementation @Inject constructor(
    val remoteDataSource: RemoteDataSourceInt,
    val localDao: LocalDatabaseDao
) : IRepo {
    override suspend fun getMovieList(key:String,lang:String,page:Int): MovieRequest {
        return remoteDataSource.getPopularMovies(key,lang,page)
    }

    override suspend fun getMovieLocalList(): Flow<List<Movie>> {
        return localDao.getMovieList()
    }
    override suspend fun getMovieById(idroom:Int):Movie{
        return localDao.getMovieById(idroom)
    }

    override suspend fun getTrailer(id: Int,key:String,lang:String): Video {
        return remoteDataSource.getTrailer(id,key,lang)
    }

    override suspend fun getTopRated(key:String,lang:String,page:Int): MovieRequest {
        return remoteDataSource.getTopRated(key,lang,page)
    }

    override suspend fun getLocalTopRated(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
}