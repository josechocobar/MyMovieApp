package com.cuty.mymovieapp.data.local

import com.cuty.mymovieapp.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceInterface {
    suspend fun getAllPopularMovies(): Flow<List<Movie>>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(item: Movie)
    suspend fun getMovies(typeOfMovie: Int):Flow<List<Movie>>
}