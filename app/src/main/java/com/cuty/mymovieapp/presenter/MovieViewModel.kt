package com.cuty.mymovieapp.presenter

import androidx.lifecycle.ViewModel
import com.cuty.mymovieapp.data.domain.RepoImplementation
import com.cuty.mymovieapp.data.models.Cast
import com.cuty.mymovieapp.data.models.Credits
import com.cuty.mymovieapp.data.models.Crew
import com.cuty.mymovieapp.data.models.Trailer
import com.cuty.mymovieapp.utils.Constants.API_KEY
import com.cuty.mymovieapp.utils.Constants.LANG_ENG
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val repoImplementation: RepoImplementation): ViewModel() {
    var listOfTrailers = flowOf(emptyList<Trailer>())
    var listOfCast = flowOf(emptyList<Cast>())
    var listOfCrew = flowOf(emptyList<Crew>())
    var director = flowOf("")

    suspend fun getCredits(id: Int): Credits {
        return repoImplementation.getCredits(id)
    }

    fun setCast(id: Int){
        listOfCast = flow { getCredits(id).cast}
    }
    fun setCrew(id: Int){
        listOfCrew= flow { getCredits(id).crew }
    }
    fun setTrailers(id: Int){
        listOfTrailers = flow { repoImplementation.remoteDataSource.getTrailer(id,API_KEY, LANG_ENG) }
    }
    fun setMovieData(id: Int){
        setCast(id)
        setCrew(id)
        setTrailers(id)
    }

}