package com.cuty.mymovieapp.data.local

import com.cuty.mymovieapp.data.models.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(var appdatadase:AppDatabase): LocalDataSourceInterface {
    override fun getAllPopularMovies(): Flow<List<Movie>> {
        return appdatadase.localDatabaseDao().getMovieList()
    }

    override fun getMovieById(idRoom:Int): Flow<Movie> {
        return appdatadase.localDatabaseDao().getMovieById(idRoom)
    }

    override suspend fun insertMovie(movie: Movie) {
        return appdatadase.localDatabaseDao().insertItem(movie)
    }

    override suspend fun deleteAll() {
        appdatadase.localDatabaseDao().deleteAll()
    }

    override suspend fun deleteMovie(item:Movie) {
        return appdatadase.localDatabaseDao().deleteItem(item)
    }

     override fun getMovieByTitle(name: String): Flow<List<Movie>> {
        return appdatadase.localDatabaseDao().getMovieListByName(name)
    }

    override fun getOnlyPopularMovies(popular:Boolean): Flow<List<Movie>> {
        return appdatadase.localDatabaseDao().getMovieList(popular)
    }

    override fun getOnlyTopRatedMovies(topRated: Boolean): Flow<List<Movie>> {
        return appdatadase.localDatabaseDao().getTopRatedMovieList(topRated)
    }

    override suspend fun getMovieByOriginalTitle(name: String): Movie {
        return appdatadase.localDatabaseDao().getMovieByOriginalTitle(name)
    }

    override suspend fun existThisMovie(name: String): Boolean {
        return appdatadase.localDatabaseDao().existThisMovie(name)
    }
}