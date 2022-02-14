package com.cuty.mymovieapp.data.domain

import com.cuty.mymovieapp.data.local.LocalDataSource
import com.cuty.mymovieapp.data.local.LocalDataSourceInterface
import com.cuty.mymovieapp.data.local.LocalDatabaseDao
import com.cuty.mymovieapp.data.models.Credits
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.MovieRequest
import com.cuty.mymovieapp.data.models.Video
import com.cuty.mymovieapp.data.remote.RemoteDataSourceInt
import com.cuty.mymovieapp.utils.Constants.API_KEY
import com.cuty.mymovieapp.utils.Constants.LANG_ENG
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepoImplementation @Inject constructor(
    val remoteDataSource: RemoteDataSourceInt,
    val localDataSource: LocalDataSourceInterface
) : IRepo {
    override suspend fun getMovieList(key:String,lang:String,page:Int): MovieRequest {
        return remoteDataSource.getPopularMovies(key,lang,page)
    }

    override fun getPopularMovieList(type: Boolean): Flow<List<Movie>> {
        return localDataSource.getOnlyPopularMovies(true)
    }

    override fun getTopRatedMovieList(type: Boolean): Flow<List<Movie>> {
        return localDataSource.getOnlyTopRatedMovies(true)
    }

    override fun getMovieLocalList(): Flow<List<Movie>> {
        return localDataSource.getAllPopularMovies()
    }
    override fun getMovieById(idroom:Int):Flow<Movie>{
        return localDataSource.getMovieById(idroom)
    }

    override suspend fun getTrailer(id: Int): Video {
        return remoteDataSource.getTrailer(id,API_KEY, LANG_ENG)
    }

    override suspend fun getTopRated(key:String,lang:String,page:Int): MovieRequest {
        return remoteDataSource.getTopRated(key,lang,page)
    }

    override fun getMovieByTitle(name: String): Flow<List<Movie>> {
        return localDataSource.getMovieByTitle(name)
    }

    override suspend fun insertMovie(movie: Movie) {
        localDataSource.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        localDataSource.deleteMovie(movie)
    }

    override suspend fun deleteAll() {
        localDataSource.deleteAll()
    }

    override suspend fun existThisMovie(name: String): Boolean {
        return localDataSource.existThisMovie(name)
    }

    override suspend fun getMovieByOriginalTitle(name: String): Movie {
        return localDataSource.getMovieByOriginalTitle(name)
    }

    override suspend fun getCredits(id: Int): Credits {
        return remoteDataSource.getCredits(id)
    }
}