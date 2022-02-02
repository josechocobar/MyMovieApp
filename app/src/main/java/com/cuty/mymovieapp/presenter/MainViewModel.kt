package com.cuty.mymovieapp.presenter

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.cuty.mymovieapp.data.domain.RepoImplementation
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.utils.Constants.API_KEY
import com.cuty.mymovieapp.utils.Constants.LANG_ENG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation
) : ViewModel() {
    var title: String =""

    fun getMovieByName(name:String){
        title=name
        Log.d(ContentValues.TAG,"la lista de busqueda es $title")

        //return filterList.filter { title-> title.title.contains(name) }
    }

    fun getMovies(): Flow<List<Movie>> {
        return if (title ==""){
            repoImplementation.localDao.getMovieList()
        } else{
            repoImplementation.localDao.getMovieListByName(title)
        }

    }


    suspend fun actualDb(){
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
    }
}