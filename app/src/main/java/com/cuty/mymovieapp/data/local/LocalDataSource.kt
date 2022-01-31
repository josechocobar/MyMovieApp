package com.cuty.mymovieapp.data.local

import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.TopRated
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(var appdatadase:AppDatabase): LocalDataSourceInterface {
    override suspend fun getAllPopularMovies(): Flow<List<Movie>> {
        return appdatadase.localDatabaseDao().getMovieList()
    }

    override suspend fun insertMovie(movie: Movie) {
        return appdatadase.localDatabaseDao().insertItem(movie)
    }

    override suspend fun deleteMovie(item:Movie) {
        return appdatadase.localDatabaseDao().deleteItem(item)
    }

    override suspend fun getTopRated(): Flow<List<TopRated>> {
        return appdatadase.localDatabaseDao().getTopRated()
    }
}