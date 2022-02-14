package com.cuty.mymovieapp.data.local

import com.cuty.mymovieapp.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceInterface {
    fun getAllPopularMovies(): Flow<List<Movie>>
    fun getMovieById(idRoom:Int):Flow<Movie>
    fun getMovieByTitle(name:String):Flow<List<Movie>>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(item: Movie)
    suspend fun deleteAll()
    fun getOnlyPopularMovies(popular: Boolean): Flow<List<Movie>>
    fun getOnlyTopRatedMovies(topRated:Boolean):Flow<List<Movie>>
    suspend fun getMovieByOriginalTitle(name: String): Movie
    suspend fun existThisMovie(name:String):Boolean

}