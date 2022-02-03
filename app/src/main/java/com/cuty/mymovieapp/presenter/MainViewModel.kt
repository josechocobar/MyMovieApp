package com.cuty.mymovieapp.presenter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.cuty.mymovieapp.data.domain.RepoImplementation
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.utils.Constants.API_KEY
import com.cuty.mymovieapp.utils.Constants.LANG_ENG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation
) : ViewModel() {


    var getPopularMovies: Flow<List<Movie>> = repoImplementation.localDao.getMovieList()
    fun getMoviebyType(typeOfMovie: Int): Flow<List<Movie>> {
        return repoImplementation.localDao.getMovieList(typeOfMovie)
    }

    suspend fun getMovieByName(name: String): Flow<List<Movie>> {
        return repoImplementation.localDao.getMovieListByName(name)
    }


    suspend fun actualDb() {
        val fetchPopularMovieData = try {
            repoImplementation.remoteDataSource.getPopularMovies(API_KEY, LANG_ENG, 1)
        } catch (e: Exception) {
            throw Exception("remote repo error", e)
        }
        fetchPopularMovieData.results.let { list ->
            repoImplementation.localDao.deleteAll()
            list?.forEach { movie ->
                repoImplementation.localDao.insertItem(movie)
            } ?: throw Exception("no results")
        }
        Log.d(ContentValues.TAG, "upgraded db")
    }
}