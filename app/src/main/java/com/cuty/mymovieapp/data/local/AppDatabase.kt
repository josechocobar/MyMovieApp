package com.cuty.mymovieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cuty.mymovieapp.data.models.Movie
import com.cuty.mymovieapp.data.models.TopRated
import com.cuty.mymovieapp.utils.DataTypeConverter

@Database(entities = [Movie::class,TopRated::class],version = 1,exportSchema = false)
@TypeConverters(value = [DataTypeConverter::class])
abstract class AppDatabase : RoomDatabase(){
    abstract fun localDatabaseDao():LocalDatabaseDao
}