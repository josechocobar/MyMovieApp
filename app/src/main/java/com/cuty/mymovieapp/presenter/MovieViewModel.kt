package com.cuty.mymovieapp.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cuty.mymovieapp.data.domain.RepoImplementation
import com.cuty.mymovieapp.data.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation,
) : ViewModel() {
    var listOfTrailers = flowOf<Video>()
    var listOfCast = flowOf(emptyList<Cast>())
    var listOfCrew = flowOf(emptyList<Crew>())
    /*
    fun makeFlow() = flow {
        println("sending first value")
        emit(1)
        println("first value collected, sending another value")
        emit(2)
        println("second value collected, sending a third value")
        emit(3)
        println("done")
    }


    fun flowObserver(){
        lifecycle.coroutineScope.launch {
            viewmodel.makeFlow().collect { value ->
                println("got $value")
            }
            println("flow is completed")
        }
    }
     */
    fun getMovie(id: Int) = flow{
        try {
            emit(repoImplementation.localDataSource.getMovieById(id))
        }catch (e: Exception) {
            println(e.message)
        }

    }
    fun getTrailers(id: Int) = flow {
        try {
            repoImplementation.getTrailer(id).results?.let { it -> emit(filterYoutube(it)) }
        } catch (e: Exception) {
            println(e.message)
        }
    }
    private fun filterYoutube(listOfTrailer:List<Trailer>):List<Trailer>{
        return listOfTrailer.filter { it.site == "YouTube" }
    }



    fun getCast(id: Int) = flow{
        try {
            emit(repoImplementation.getCredits(id).cast.subList(0,3))
        }catch (e:Exception){
            println(e.message)
        }
    }
}