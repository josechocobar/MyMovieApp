package com.cuty.mymovieapp.data.remote.cases

import com.cuty.mymovieapp.data.models.Movie

class Cases {
    /*
    val fetchPopularMovieData = try {
            repoImplementation.remoteDataSource.getPopularMovies(API_KEY, LANG_ENG,1)
        } catch (e: Exception) {
            throw Exception("remote repo error",e)
        }
        repoImplementation.localDao.deleteAll()
        fetchPopularMovieData.results?.forEach {
            repoImplementation.localDao.insertItem(it)
        } ?: throw Exception("no results")
        Log.d(ContentValues.TAG,"upgraded db")
     */
    /*
    1->TopRated
    2->Popular
     */
    fun asignAsTopRated(movie:Movie): Movie {
        movie.typeOfMovie = 1
        return movie
    }
    fun asignAsPopular(movie: Movie):Movie{
        movie.typeOfMovie = 2
        return movie
    }
    fun getTopRatedFromServer(){
        val fetchTopRated= try {

        }
    }


}