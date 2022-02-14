package com.cuty.mymovieapp.data.local

import androidx.room.*
import com.cuty.mymovieapp.data.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDatabaseDao {
    @Query("SELECT * FROM movie_table ORDER BY idroom")
    fun getMovieList(): Flow<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE popular=:popular ORDER BY idroom")
    fun getMovieList(popular:Boolean): Flow<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE top_rated=:topRated ORDER BY idroom")
    fun getTopRatedMovieList(topRated : Boolean): Flow<List<Movie>>

    @Query("SELECT * FROM movie_table WHERE original_title=:originalTitle")
    suspend fun getMovieByOriginalTitle(originalTitle : String):Movie

    @Query("SELECT * FROM movie_table WHERE idroom=:id ")
    fun getMovieById(id:Int):Movie

    @Query("SELECT * FROM movie_table WHERE title LIKE '%' || :title || '%'")
    fun getMovieListByName(title: String):Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(movie: Movie)

    @Delete
    suspend fun deleteItem(item:Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()

    @Query("SELECT EXISTS (SELECT 1 FROM movie_table WHERE original_title=:title)")
    suspend fun existThisMovie(title:String):Boolean

}